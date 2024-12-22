package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetails;
import java.time.Duration;

public class ProductDetailsSteps {
    WebDriver driver;
    ProductDetails productDetails;
    HomePage homePage;
    LoginPage loginPage;

    By loginLink = By.xpath("//a[@id='login2']");

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    @Given("I am logged into the application")
    public void i_am_logged_into_the_application() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productDetails = new ProductDetails(driver);

        homePage.clickLogin();
        loginPage.enterUsername("testuser25177");
        loginPage.enterPassword("1234");
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
    }

    String productDetailsUrl;
    @When("I click on a product")
    public void i_click_on_a_product() {
        productDetails.clickOnProduct();
        productDetailsUrl = driver.getCurrentUrl();
    }

    @Then("the product name should not be empty")
    public void the_product_name_should_not_be_empty() {
        Assert.assertFalse(productDetails.getProductName().isEmpty(), "Product name is missing.");
    }

    @Then("the product price should contain \"$\"")
    public void the_product_price_should_contain_dollar() {
        Assert.assertTrue(productDetails.getProductPrice().contains("$"), "Product price does not contain '$'.");
    }

    @Then("the product description should contain {string}")
    public void the_product_description_should_contain(String expectedDescription) {
        Assert.assertTrue(productDetails.getProductDescription().contains(expectedDescription),
                "Product description does not contain the expected text.");
    }

    @Then("the product image should be visible")
    public void the_product_image_should_be_visible() {
        Assert.assertNotNull(productDetails.getProductImage().getAttribute("src"), "Product image is missing.");
    }

    @Then("the Add to Cart button should be visible")
    public void the_add_to_cart_button_should_be_visible() {
        Assert.assertTrue(productDetails.getAddToCartButton().isDisplayed(), "Add to Cart button is not visible.");
    }

    @When("I click on the Add to Cart button")
    public void i_click_on_the_add_to_cart_button() {
        productDetails.clickAddToCartButton();
    }

    @Then("I should see a success alert")
    public void i_should_see_a_success_alert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            acceptAlert();
        } catch (TimeoutException e) {
            Assert.fail("No success alert displayed: " + e.getMessage()); // Log exception message
        }
    }

    @Then("the product description should have more than {int} words")
    public void the_product_description_should_have_more_than_words(int wordCount) {
        String description = productDetails.getProductDescription();
        int actualWordCount = description.split("\\s+").length;
        Assert.assertTrue(actualWordCount > wordCount, "Product description does not have enough words.");
    }

    @Then("the product price should be greater than zero")
    public void the_product_price_should_be_greater_than_zero() {
        String priceText = productDetails.getProductPrice();
        String priceString = priceText.replaceAll("[^0-9]", "");
        int price = Integer.parseInt(priceString);
        Assert.assertTrue(price > 0, "Product price is not greater than zero.");
    }

    @Then("the product description should not exceed {int} characters")
    public void the_product_description_should_not_exceed_characters(int charLimit) {
        String description = productDetails.getProductDescription();
        Assert.assertTrue(description.length() <= charLimit, "Product description exceeds the character limit.");
    }

    private boolean isAlertPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            return alert != null;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private void acceptAlert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}