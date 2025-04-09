package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProductTitleByIndex(int index) {
        return driver.findElements(By.className("inventory_item_name")).get(index);
    }

    public WebElement getAddToCartButtonByProductName(String productName) {
        return driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"));
    }

    public void addProductToCart(String productName) {
        getAddToCartButtonByProductName(productName).click();
    }

    public WebElement getSortDropdown() {
        return driver.findElement(By.className("product_sort_container"));
    }

    public void sortBy(String visibleText) {
        getSortDropdown().sendKeys(visibleText);
    }

    public WebElement getCartBadge() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public void openCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public boolean isProductNameVisible() {
        return driver.findElement(By.className("inventory_item_name")).isDisplayed();
    }

    public boolean isAddToCartVisible() {
        return driver.findElement(By.cssSelector(".btn_inventory")).isDisplayed();
    }
    public void logout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        try {
            Thread.sleep(500); // ممكن تغيره لـ WebDriverWait بعدين
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("logout_sidebar_link")).click();
    }


}
