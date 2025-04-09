package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {
    private WebDriver driver;

    // Constructor
    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Elements
    private WebElement getUsernameField() {
        return driver.findElement(By.id("user-name"));
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.id("password"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector("h3[data-test='error']"));
    }

    // Actions
    public void enterUsername(String username) {
        getUsernameField().clear();
        getUsernameField().sendKeys(username);
    }

    public void enterPassword(String password) {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
    }

    public void clickLogin() {
        getLoginButton().click();
    }

    public String getErrorText() {
        return getErrorMessage().getText();
    }

    public boolean isErrorDisplayed() {
        try {
            return getErrorMessage().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
