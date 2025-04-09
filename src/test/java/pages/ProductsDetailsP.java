package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsDetailsP {
    private static WebDriver driver;

    public ProductsDetailsP(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProductNameElement() {
        return driver.findElement(By.className("inventory_details_name"));
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(By.cssSelector("button.btn_inventory"));
    }

    public static void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }


    public void goBackToInventory() {
        WebElement backButton = driver.findElement(By.id("back-to-products"));
        backButton.click();
    }

    public void addProductToCart() {
    }
}
