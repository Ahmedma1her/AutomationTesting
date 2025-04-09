package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutPageTest extends BaseTest {

    @Test
    public void testSuccessfulCheckoutFlow() {
        // Login
        loginPage login = new loginPage(basedriver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        // Add to cart
        ProductsPage productsPage = new ProductsPage(basedriver);
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();

        // Go to checkout
        CartPage cartPage = new CartPage(basedriver);
        cartPage.clickCheckout();

        // Fill info and continue
        CheckoutPage checkout = new CheckoutPage(basedriver);
        checkout.enterCheckoutInfo("Peter", "Tester", "12345");
        checkout.clickContinue();

        // Step Two (Overview)
        String url = basedriver.getCurrentUrl();
        Assert.assertTrue(url.contains("checkout-step-two"), "Not on overview page!");

        // Finish
        checkout.clickFinish();
        String confirmation = checkout.getConfirmationMessage();
        Assert.assertEquals(confirmation, "Thank you for your order!");
    }

    @Test
    public void testInvalidPostalCode_letters() {
        loginPage login = new loginPage(basedriver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductsPage productsPage = new ProductsPage(basedriver);
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();

        CartPage cartPage = new CartPage(basedriver);
        cartPage.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(basedriver);
        checkout.enterCheckoutInfo("Maher", "Tester", "ABCDE");
        checkout.clickContinue();

        Assert.assertTrue(checkout.isErrorDisplayed());
    }

    @Test
    public void testMissingFirstName() {
        loginPage login = new loginPage(basedriver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductsPage productsPage = new ProductsPage(basedriver);
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.openCart();

        CartPage cartPage = new CartPage(basedriver);
        cartPage.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(basedriver);
        checkout.enterCheckoutInfo("", "Tester", "12345");
        checkout.clickContinue();

        Assert.assertTrue(checkout.isErrorDisplayed());
        Assert.assertTrue(checkout.getErrorMessage().contains("First Name"));
    }
}
