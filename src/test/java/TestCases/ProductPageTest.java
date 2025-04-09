package TestCases;

import Data.UserData;
import org.openqa.selenium.WebDriver;
import pages.loginPage;
import pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductPageTest extends BaseTest {

    @Test(dataProvider = "workingUsers", dataProviderClass = UserData.class)
    public void testAddAndRemoveProducts(String username, String password) {
        // Step 1: Login
        loginPage loginPage = new loginPage(basedriver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        WebDriver driver ;

        // Step 2: Initialize ProductsPage
        ProductsPage productPage = new ProductsPage(basedriver);

        // Verify that we're on the inventory page
        String currentUrl = basedriver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "User should be redirected to the inventory page.");

        // Step 3: For all six products: add to cart then remove
        for (int i = 0; i < 6; i++) {
            // Get the product title by index and extract its name
            WebElement productTitleElement = productPage.getProductTitleByIndex(i);
            String productName = productTitleElement.getText();

            // Click the "Add to cart" button for this product
            productPage.addProductToCart(productName);

            // Verify that the button text changes to "Remove"
            WebElement buttonAfterAdd = productPage.getAddToCartButtonByProductName(productName);
            String buttonTextAfterAdd = buttonAfterAdd.getText();
            Assert.assertEquals(buttonTextAfterAdd, "Remove",
                    "After clicking 'Add to cart', the button should display 'Remove' for product: " + productName);

            // Now click the "Remove" button to remove the product from the cart
            buttonAfterAdd.click();

            // Verify that the button text changes back to "Add to cart"
            WebElement buttonAfterRemove = productPage.getAddToCartButtonByProductName(productName);
            String buttonTextAfterRemove = buttonAfterRemove.getText();
            Assert.assertEquals(buttonTextAfterRemove, "Add to cart",
                    "After clicking 'Remove', the button should revert to 'Add to cart' for product: " + productName);
        }

        // Step 4: Logout and verify
        productPage.logout();
        Assert.assertTrue(basedriver.getCurrentUrl().contains("saucedemo"), "Logout failed!");
    }
}