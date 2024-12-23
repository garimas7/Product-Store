package stepsDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CheckoutPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private WebDriverWait wait;

    // Locators
    private final By loginLink = By.xpath("//a[@id='login2']");
    private final By usernameField = By.xpath("//input[@id='loginusername']");
    private final By passwordField = By.xpath("//input[@id='loginpassword']");
    private final By loginButton = By.xpath("//button[normalize-space()='Log in']");
    private final By firstProduct = By.xpath("//a[normalize-space()='Samsung galaxy s6']");
    private final By addToCartButton = By.xpath("//a[normalize-space()='Add to cart']");
    private final By cartButton = By.id("cartur");

    @Before
    public void setup() {
    	System.out.println("Initializing WebDriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        checkoutPage = new CheckoutPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void teardown() {
    	System.out.println("Closing WebDriver");
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the demoblaze website")
    public void i_am_on_the_demoblaze_website() {
        driver.get("https://www.demoblaze.com/");
    }

    @Given("I am logged in as {string} with password {string}")
    public void i_am_logged_in_as_with_password(String username, String password) throws InterruptedException {
        login(username, password);
    }

    @When("I add a product to cart")
    public void i_add_a_product_to_cart() throws InterruptedException {
        addProductToCart();
    }

    @When("I navigate to cart")
    public void i_navigate_to_cart() {
        checkoutPage.clickCartButton();
    }

    @When("I click on place order")
    public void i_click_on_place_order() {
        checkoutPage.clickPlaceOrder();
    }

    @When("I fill checkout details with following information")
    public void i_fill_checkout_details_with_following_information(DataTable dataTable) {
        fillCheckoutDetails(dataTable);
    }

    @When("I click on purchase button")
    public void i_click_on_purchase_button() throws InterruptedException {
        checkoutPage.clickPurchaseButton();
        Thread.sleep(3000);
    }

    @Then("I should see successful purchase message")
    public void i_should_see_successful_purchase_message() {
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "Purchase was not successful");
    }

    @Then("I should see alert message {string}")
    public void i_should_see_alert_message(String expectedMessage) throws InterruptedException {
        String actualMessage = handleAlert();
        Assert.assertEquals(actualMessage, expectedMessage, "Alert message doesn't match");
    }

    @When("I add {string} products to cart")
    public void i_add_products_to_cart(String count) {
        addMultipleProductsToCart(count);
    }

    @Then("I should see all items in the cart")
    public void i_should_see_all_items_in_the_cart() {
        int itemCount = checkoutPage.getCartItemCount();
        Assert.assertTrue(itemCount > 0, "Cart is empty");
    }

    @Then("cart should contain {string} item")
    public void cart_should_contain_item(String expectedCount) {
        int actualCount = checkoutPage.getCartItemCount();
        Assert.assertEquals(actualCount, Integer.parseInt(expectedCount), 
            "Cart item count doesn't match");
    }

    // Helper methods for code reuse
    private void login(String username, String password) throws InterruptedException {
    	driver.findElement(loginLink).click();
    	driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
    }

    private void addProductToCart() throws InterruptedException {
    	driver.findElement(firstProduct).click();
    	Thread.sleep(3000);
    	driver.findElement(addToCartButton).click();
    	Thread.sleep(3000);
        handleAlert();
    }

    private void fillCheckoutDetails(DataTable dataTable) {
        try {
            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            Map<String, String> checkoutData = data.get(0);
            
            // Check for empty or missing values and handle them
            String name = checkoutData.get("name");
            String card = checkoutData.get("card");
            
            // If required fields are empty, handle the validation or alert here
            if (name == null || name.isEmpty() || card == null || card.isEmpty()) {
                // Trigger the alert here (this depends on your webpage behavior)
                throw new RuntimeException("Please fill out Name and Creditcard.");
            }

            // Proceed with filling checkout details
            checkoutPage.fillCheckoutDetails(
                name,
                checkoutData.get("country"),
                checkoutData.get("city"),
                card,
                checkoutData.get("month"),
                checkoutData.get("year")
            );
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to fill checkout details: " + e.getMessage());
        }
    }

    private void addMultipleProductsToCart(String count) {
        try {
            int itemCount = Integer.parseInt(count);
            for (int i = 0; i < itemCount; i++) {
                addProductToCart();
                driver.findElement(By.xpath("//a[@id='nava']")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));  // Ensure page loads correctly
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to add multiple products: " + e.getMessage());
        }
    }

    private String handleAlert() throws InterruptedException {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            Thread.sleep(3000);
            alert.accept();
            Thread.sleep(3000);
            return alertMessage;
        } catch (TimeoutException | NoAlertPresentException e) {
            return "";
        }
    }
}
