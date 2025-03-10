package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;

public class LoginTestCase extends BaseTest {
    @Test
    public void testValidLogin() {
        LoginPage LP;
        LP=new LoginPage(basedriver);
        LoginPage.enterusername("standard_user");
        LoginPage.enterpassword("secret_sauce");
        LoginPage.clickLogin();

    }
}

