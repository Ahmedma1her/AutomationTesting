package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.loginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.List;

public class CartPageTest extends BaseTest {

    @Test
    public void testCartPageForSingleUser() {
        // Step 1: Login with static user
        loginPage login = new loginPage(basedriver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        // Step 2: Wait for products to load
        WebDriverWait wait = new WebDriverWait(basedriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        ProductsPage productsPage = new ProductsPage(basedriver);
        List<WebElement> products = basedriver.findElements(By.className("inventory_item"));
        Assert.assertFalse(products.isEmpty(), "No products found!");

        // Step 3: Get the first product name & add to cart
        WebElement firstProduct = products.get(0);
        String expectedProductName = firstProduct.findElement(By.className("inventory_item_name")).getText();
        firstProduct.findElement(By.cssSelector("button.btn_inventory")).click();

        // Step 4: Open cart page
        productsPage.openCart();

        // Wait for cart page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));

        // Step 5: Verify cart contents
        CartPage cartPage = new CartPage(basedriver);
        List<WebElement> cartItems = cartPage.getCartItems();
        Assert.assertFalse(cartItems.isEmpty(), "Cart is empty!");

        String actualProductName = cartPage.getFirstProductName();
        Assert.assertEquals(actualProductName, expectedProductName, "Product name in cart does not match!");

        // Step 6: Logout
        productsPage.logout();
    }
}
