package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ShoppingCartPage;
import java.time.Duration;

public class CheckoutPageTest {
    WebDriver driver;
    CheckoutPage checkoutPage;
    ShoppingCartPage shoppingCartPage;

    public String waitForAlertAndAccept() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();
        alert.accept();
        return alertMessage;
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        checkoutPage = new CheckoutPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void registeredUserCheckout() throws InterruptedException {
    	shoppingCartPage.enterUsername("testuser25177");
    	shoppingCartPage.enterPassword("1234");
    	shoppingCartPage.clickLoginButton();
        Thread.sleep(3000);
        shoppingCartPage.addProduct();
        checkoutPage.clickCartButton();
        checkoutPage.clickPlaceOrder();
        checkoutPage.fillCheckoutDetails("Garima Singh", "India", "Delhi", "1234567890", "05", "2024");
        checkoutPage.clickPurchaseButton();
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "Purchase was not successful.");
    }

    @Test(priority = 2)
    public void guestUserCheckout() {
        shoppingCartPage.addProduct();
        String alertMessage = waitForAlertAndAccept();
        System.out.println("Alert after adding product: " + alertMessage); 
        checkoutPage.clickCartButton();
        checkoutPage.clickPlaceOrder();
        checkoutPage.fillCheckoutDetails("Guest User", "Uttar Pradesh", "Varanasi", "9876543210987654", "01", "2026");
        checkoutPage.clickPurchaseButton();
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "Guest user purchase was not successful.");
    }

    @Test(priority = 3)
    public void guestUserMissingDetails() {
        shoppingCartPage.addProduct();
        String alertMessage = waitForAlertAndAccept();
        System.out.println("Alert after adding product: " + alertMessage); 
        checkoutPage.clickCartButton();
        checkoutPage.clickPlaceOrder();
        checkoutPage.fillCheckoutDetails("", "India", "Delhi", "", "01", "2026"); // Missing name and card number
        checkoutPage.clickPurchaseButton();
        String alertText = waitForAlertAndAccept();
        Assert.assertEquals(alertText, "Please fill out Name and Creditcard.", "Alert text does not match expected.");
        Assert.assertFalse(checkoutPage.isPurchaseSuccessful(), "Purchase should not be successful with missing details.");
    }

    @Test(priority = 4)
    public void registeredUserMissingDetails() {
    	shoppingCartPage.enterUsername("testuser25177");
    	shoppingCartPage.enterPassword("1234");
    	shoppingCartPage.clickLoginButton();
        shoppingCartPage.addProduct();
        checkoutPage.clickCartButton();
        checkoutPage.clickPlaceOrder();
        checkoutPage.fillCheckoutDetails("", "India", "", "", "", ""); // Missing all fields
        String alertText = waitForAlertAndAccept();
        Assert.assertEquals(alertText, "Please fill out Name and Creditcard.", "Alert text does not match expected.");
        Assert.assertFalse(checkoutPage.isPurchaseSuccessful(), "Purchase should not be successful with missing details.");
    }

    @Test(priority = 5)
    public void maximumItemsInCart() throws InterruptedException {
    	shoppingCartPage.enterUsername("testuser25177");
    	shoppingCartPage.enterPassword("1234");
    	shoppingCartPage.clickLoginButton();
        Thread.sleep(3000);
        for (int i = 0; i < 50; i++) {
            shoppingCartPage.addProduct();
            @SuppressWarnings("unused")
			String alertText = waitForAlertAndAccept();
            driver.findElement(By.xpath("//a[@id='nava']")).click();
            Thread.sleep(3000);
        }
        checkoutPage.clickCartButton();
        int itemsCount = checkoutPage.getCartItemCount();
        Assert.assertEquals(checkoutPage.getCartItemCount(), itemsCount, "Cart does not contain the maximum number of items.");
    }

    @Test(priority = 6)
    public void singleItemCheckout() {
        shoppingCartPage.addProduct();
        checkoutPage.clickCartButton();
        Assert.assertEquals(checkoutPage.getCartItemCount(), 1, "Cart does not contain a single item.");
        checkoutPage.clickPlaceOrder();
        checkoutPage.fillCheckoutDetails("Garima Singh", "India", "Delhi", "1234567890", "06", "2024");
        checkoutPage.clickPurchaseButton();
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "Single item purchase was not successful.");
    }
}
