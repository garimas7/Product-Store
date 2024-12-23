package stepsDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class ShoppingCartSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Given("the user navigates to the shopping site")
    public void the_user_navigates_to_the_shopping_site() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(3000);
    }

    @When("the user clicks on the cart button")
    public void the_user_clicks_on_the_cart_button() {
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cartur']")));
        cartButton.click();
    }

    @Then("the cart page should load successfully")
    public void the_cart_page_should_load_successfully() {
        WebElement cartPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Products']")));
        Assert.assertTrue(cartPageTitle.isDisplayed(), "Cart page did not load successfully");
    }

    @Then("the cart page title should be {string}")
    public void the_cart_page_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match");
    }

    @Then("the cart page URL should contain {string}")
    public void the_cart_page_url_should_contain(String expectedUrlFragment) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedUrlFragment), "URL does not contain the expected fragment");
    }

    @Given("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() throws InterruptedException {
    	Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@id='login2']")).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("testuser25177");
        driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("1234");
        driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
    }

    @Then("the username {string} should be displayed in the navigation bar")
    public void the_username_should_be_displayed_in_the_navigation_bar(String expectedUsername) {
        WebElement usernameDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nameofuser']")));
        Assert.assertTrue(usernameDisplay.getText().contains(expectedUsername), "Username is not displayed correctly");
    }

    @Given("the user adds a product to the cart")
    public void the_user_adds_a_product_to_the_cart() {
        driver.findElement(By.xpath("//a[normalize-space()='Samsung galaxy s6']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add to cart']"))).click();
        driver.switchTo().alert().accept();
    }

    @When("the user removes the product from the cart")
    public void the_user_removes_the_product_from_the_cart() {
        driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]")).click();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        WebElement cartTable = driver.findElement(By.xpath("//tbody"));
        Assert.assertTrue(cartTable.getText().isEmpty(), "Cart is not empty");
    }

    @Given("the user opens the login page")
    public void the_user_opens_the_login_page() {
        driver.findElement(By.xpath("//a[@id='login2']")).click();
    }

    @When("the user tries to log in with a missing password")
    public void the_user_tries_to_log_in_with_a_missing_password() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("testuser25177");
        driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedErrorMessage) {
    	String alerttxt = driver.switchTo().alert().getText();
        Assert.assertEquals(alerttxt, expectedErrorMessage, "Error message does not match");
    }

    @When("the user places an order")
    public void the_user_places_an_order() {
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        driver.findElement(By.id("name")).sendKeys("Test User");
        driver.findElement(By.id("country")).sendKeys("USA");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("card")).sendKeys("1234567890123456");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2025");
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void the_order_confirmation_message_should_be_displayed(String expectedMessage) {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]")));
        Assert.assertTrue(confirmationMessage.getText().contains(expectedMessage), "Confirmation message does not match");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}