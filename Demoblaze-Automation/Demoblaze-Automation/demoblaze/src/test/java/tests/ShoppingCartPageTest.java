package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.ShoppingCartPage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ShoppingCartPageTest{

    WebDriver driver;
    ShoppingCartPage shoppingcartPage;
    
    public String handleAlert() {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept(); // or alert.dismiss();
        return message;
    }
    public void waitForAlertAndAccept() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent()); // Wait for the alert
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		System.out.println("Alert found with message: " + alertMessage);
		alert.accept(); // Accept the alert
    }
    public void login(String username, String password) {
        shoppingcartPage.navigateToLoginModal();
        shoppingcartPage.enterUsername(username);
        shoppingcartPage.enterPassword(password);
        shoppingcartPage.clickLoginButton();
    }
    
	@BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Navigate to the web site
        driver.get("https://www.demoblaze.com/");
        shoppingcartPage = new ShoppingCartPage(driver);
    }

    @Test(priority = 1)
    public void testCartPageLoadsSuccessfully() throws InterruptedException {
    	shoppingcartPage.clickCartButton();
        Assert.assertTrue(shoppingcartPage.isCartPageLoaded(), "Cart page did not load successfully.");
    }

    @Test(priority = 2)
    public void testValidateTitleAndURL() throws InterruptedException {
    	shoppingcartPage.clickCartButton();
        String expectedTitle = "STORE";
        String expectedURL = "https://www.demoblaze.com/cart.html";
        Assert.assertEquals(shoppingcartPage.getPageTitle(), expectedTitle, "Page title mismatch.");
        Assert.assertEquals(shoppingcartPage.getPageURL(), expectedURL, "Page URL mismatch.");
    }

    @Test(priority = 3)
    public void testUsernameDisplayedInNavigationBar() throws InterruptedException {
    	shoppingcartPage.navigateToLoginModal();
    	shoppingcartPage.enterUsername("testuser25177");
    	shoppingcartPage.enterPassword("1234");
    	shoppingcartPage.clickLoginButton();
    	Thread.sleep(3000);
        Assert.assertTrue(shoppingcartPage.isUsernameDisplayed(), "Username is not displayed in the navigation bar.");
    }

    @Test(priority = 4)
    public void testRemoveProductFromCart() throws InterruptedException {
    	shoppingcartPage.navigateToLoginModal();
    	shoppingcartPage.enterUsername("testuser25177");
    	shoppingcartPage.enterPassword("1234");
    	shoppingcartPage.clickLoginButton();
    	shoppingcartPage.clickCartButton();
    	shoppingcartPage.deleteProductFromCart();
    	Thread.sleep(3000);
    	WebElement tableBody = driver.findElement(By.xpath("//tbody"));
    	List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
    	if (rows.isEmpty()) {
            System.out.println("The table is empty.");
        } else {
            System.out.println("The table is not empty. Row count: " + rows.size());
        }
    }

    @Test(priority = 5)
    public void testLoginWithMissingRequiredFields() throws InterruptedException {
    	shoppingcartPage.navigateToLoginModal();
    	shoppingcartPage.enterUsername(""); // Missing username
    	shoppingcartPage.enterPassword("");	// Missing password
    	shoppingcartPage.clickLoginButton();
    	String alertMessage = handleAlert(); // Handle the alert
        Assert.assertNotNull(alertMessage, "Alert should appear for empty username.");
        Assert.assertTrue(alertMessage.contains("Please fill out Username and Password."), 
            "Incorrect error message for empty username.");
        Assert.assertFalse(shoppingcartPage.isWelcomeMessageVisible(), 
            "Welcome message should not be visible.");
    }

    @Test(priority = 6)
    public void testLoginWithEmptyUsername() {
    	shoppingcartPage.navigateToLoginModal();
    	shoppingcartPage.enterPassword("1234");
    	shoppingcartPage.clickLoginButton();
    	String alertMessage = handleAlert(); // Handle the alert
        Assert.assertNotNull(alertMessage, "Alert should appear for empty username.");
        Assert.assertTrue(alertMessage.contains("Please fill out Username and Password."), 
            "Incorrect error message for empty username.");
        Assert.assertFalse(shoppingcartPage.isWelcomeMessageVisible(), 
            "Welcome message should not be visible.");
    }

    @Test(priority = 7)
    public void testAddAndRemoveSingleItem() throws InterruptedException {
    	shoppingcartPage.navigateToLoginModal();
    	shoppingcartPage.enterUsername("testuser25177");
    	shoppingcartPage.enterPassword("1234");
    	shoppingcartPage.clickLoginButton();
    	Thread.sleep(3000);
    	shoppingcartPage.addAndRemoveProduct();
    	Thread.sleep(3000);
    	WebElement tableBody = driver.findElement(By.xpath("//tbody"));
    	List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
    	if (rows.isEmpty()) {
            System.out.println("The table is empty.");
        } else {
            System.out.println("The table is not empty. Row count: " + rows.size());
        }
    }

    @SuppressWarnings("unused")
	@Test(priority = 8)
    public void testPlaceOrderAfterAddingSingleItem() throws InterruptedException {
    	shoppingcartPage.navigateToLoginModal();
    	shoppingcartPage.enterUsername("testuser25177");
    	shoppingcartPage.enterPassword("1234");
    	shoppingcartPage.clickLoginButton();
    	Thread.sleep(2000);
    	shoppingcartPage.addProduct();
    	Thread.sleep(2000);
    	String alertMessage = handleAlert();
    	shoppingcartPage.clickCartButton();
    	Thread.sleep(5000);
    	shoppingcartPage.clickPlaceOrder();
    	Thread.sleep(5000);
    	WebElement confirmMsg = driver.findElement(By.xpath("//p[@class='lead text-muted ']"));
    	String msg = confirmMsg.getText();
    	System.out.println(msg);
    	Assert.assertTrue(msg.contains("1234567890"));
    }

    @Test(priority = 9)
    public void testAddAndRemoveSingleItemTwice() throws InterruptedException {
        // Log in
        shoppingcartPage.navigateToLoginModal();
        shoppingcartPage.enterUsername("testuser25177");
        shoppingcartPage.enterPassword("1234");
        shoppingcartPage.clickLoginButton();
        Thread.sleep(3000);

        // Add the product to the cart for the first time
        shoppingcartPage.addProduct();
        Thread.sleep(3000);
        String firstAlertMessage = handleAlert();
        System.out.println("First alert message: " + firstAlertMessage);

        // Navigate back to add the product again
        driver.findElement(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")).click();
        Thread.sleep(3000);

        shoppingcartPage.addProduct(); // Adding the same product again
        Thread.sleep(3000);
        String secondAlertMessage = handleAlert();
        System.out.println("Second alert message: " + secondAlertMessage);

        // Navigate to the cart
        shoppingcartPage.clickCartButton();
        Thread.sleep(3000);

        // Remove the product from the cart for the first time
        shoppingcartPage.deleteProductFromCart();
        Thread.sleep(3000);

        // Navigate back to the cart to delete the product again
        driver.navigate().back();
        Thread.sleep(3000);

        shoppingcartPage.deleteProductFromCart();
        Thread.sleep(3000);

        // Final verification of the cart
        WebElement tableBody = driver.findElement(By.xpath("//tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        if (rows.isEmpty()) {
            System.out.println("The table is empty after both deletions.");
        } else {
            System.out.println("The table is not empty. Row count: " + rows.size());
        }
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
