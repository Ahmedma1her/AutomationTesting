package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Step One fields
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By errorMsg = By.cssSelector("h3[data-test='error']");

    // Step Two (Overview)
    By finishButton = By.id("finish");

    // Confirmation page
    By confirmationMsg = By.className("complete-header");

    // Actions for Step One
    public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).clear();
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }

    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

    // Step Two
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    // Confirmation
    public String getConfirmationMessage() {
        return driver.findElement(By.className("complete-header")).getText();
    }
}