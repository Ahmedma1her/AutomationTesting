package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(By.className("cart_item"));
    }

    public String getFirstProductName() {
        return driver.findElement(By.className("inventory_item_name")).getText();
    }

    public void removeFirstProduct() {
        driver.findElement(By.cssSelector("button.cart_button")).click();
    }

    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }
    public int getNumberOfCartItems() {
        return getCartItems().size();
    }

}
