package TestCases;

import Data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.loginPage;
import pages.ProductsDetailsP;
import pages.ProductsPage;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class ProductDetailsPageTest extends BaseTest {

    @Test(dataProvider = "workingUsers", dataProviderClass = UserData.class)
    public void testProductDetailsForAllProducts(String username, String password) {
        // Step 1: Login
        loginPage loginPage = new loginPage(basedriver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(basedriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        ProductsPage productsPage = new ProductsPage(basedriver);

        // Step 2: Get total number of products
        List<WebElement> initialProducts = basedriver.findElements(By.className("inventory_item_name"));
        Assert.assertFalse(initialProducts.isEmpty(), "No products found on the inventory page.");

        int totalProducts = initialProducts.size();

        // Step 3: Loop through each product
        ProductsDetailsP detailsPage;
        for (int i = 0; i < totalProducts; i++) {
            // مهم: إعادة تحميل العناصر كل مرة
            List<WebElement> allProducts = basedriver.findElements(By.className("inventory_item_name"));
            WebElement productElement = allProducts.get(i);
            String expectedProductName = productElement.getText();

            productElement.click();

            // Step 4: Product details checks
            detailsPage = new ProductsDetailsP(basedriver);
            wait.until(ExpectedConditions.visibilityOf(detailsPage.getProductNameElement()));

            String actualProductName = detailsPage.getProductNameElement().getText();
            Assert.assertEquals(actualProductName, expectedProductName,
                    "Product name on details page does not match the expected name.");

            // Step 5: Check Add to Cart
            wait.until(ExpectedConditions.visibilityOf(detailsPage.getAddToCartButton()));
            Assert.assertTrue(detailsPage.getAddToCartButton().isDisplayed(),
                    "Add to Cart button is not visible on the product details page.");

            detailsPage.addProductToCart();

            // Step 6: Go back to inventory
            detailsPage.goBackToInventory();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        }

        // Step 7: Go to cart and verify item count
        ProductsDetailsP.goToCart();

        CartPage cartPage = new CartPage(basedriver);
            int actualCartItems = cartPage.getNumberOfCartItems();
        Assert.assertEquals(actualCartItems, totalProducts,
                "Number of items in cart does not match number of added products.");

// Step 8: Logout
        basedriver.navigate().back(); // عشان نرجع inventory ونعمل logout
        productsPage.logout();
        Assert.assertTrue(basedriver.getCurrentUrl().contains("saucedemo"),
                "Logout failed; the URL does not indicate the login page.");
    }
}