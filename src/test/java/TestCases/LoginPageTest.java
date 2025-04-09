package TestCases;

import Data.UserData;
import pages.loginPage;
import pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(dataProvider = "UserData", dataProviderClass = UserData.class)
    public void testValidLogin(String username, String password, boolean isExpectedToLogin) {
        loginPage loginPage = new loginPage(basedriver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (!isExpectedToLogin) {
            // For users that should not log in successfully (e.g., locked_out_user)
            Assert.assertTrue(loginPage.getErrorMessage().isDisplayed(), "Expected error for locked out user.");
        } else {
            // For users expected to log in successfully
            String currentUrl = basedriver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("inventory"), "Login failed! Not redirected to inventory.");

            // Logout after verifying login
            ProductsPage productPage = new ProductsPage(basedriver);
            productPage.logout();

            // Optionally verify that we are redirected back to the login page
            Assert.assertTrue(basedriver.getCurrentUrl().contains("saucedemo"), "Logout failed!");
        }
    }
}
