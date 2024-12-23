package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetails;
import java.time.Duration;

public class ProductDetailsTest extends BaseTest { // Extend BaseTest
    WebDriverWait wait;
    ProductDetails productDetails;
    HomePage homePage;
    LoginPage loginPage;

    public boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.out.println("ProductDetailsTest - BeforeMethod: Setting up test");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout to 15 seconds
        homePage = new HomePage(driver);
        productDetails = new ProductDetails(driver);
        loginPage = new LoginPage(driver);

        driver.get("https://www.demoblaze.com/");

        // Trigger the login modal
        WebElement loginButton = driver.findElement(By.id("login2"));
        loginButton.click();

        // Wait for the login modal to appear
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
        } catch (Exception e) {
            System.err.println("Login modal not visible: " + e.getMessage());
            throw e; // Rethrow exception after logging
        }
        loginPage.enterUsername("testuser25177");
        loginPage.enterPassword("1234");
        loginPage.clickLoginButton();
        Thread.sleep(3000);
    }


    @AfterMethod
    public void tearDown() {
        System.out.println("ProductDetailsTest - AfterMethod: Tearing down test");
    }

    @SuppressWarnings("deprecation")
	@Test(priority = 1)
    public void testVerifyProductDetails() throws InterruptedException {
        productDetails.clickOnProduct();
        Thread.sleep(3000);
        Assert.assertTrue(!productDetails.getProductName().isEmpty(), "Product name is missing.");
        System.out.println(productDetails.getProductName());
        Assert.assertTrue(productDetails.getProductPrice().contains("$"), "Product price is not displayed or does not contain '$'.");
        System.out.println(productDetails.getProductPrice());
        Assert.assertTrue(productDetails.getProductDescription().contains("Samsung Galaxy S6"), "Product description is not displayed or does not contain 'Samsung Galaxy S6'.");
        System.out.println(productDetails.getProductDescription());
        Assert.assertNotNull(productDetails.getProductImage().getAttribute("src"), "Product image is missing.");
    }

    @Test(priority = 2)
    public void testAddToCartButtonVisibleAndFunctional() throws InterruptedException {
        productDetails.clickOnProduct();
        Thread.sleep(3000);
        By addToCartButtonLocator = By.xpath("//a[normalize-space()='Add to cart']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));

        Assert.assertTrue(productDetails.getAddToCartButton().isDisplayed(), "Add to Cart button is not visible.");
        System.out.println("Add to Cart Button is visible: " + productDetails.getAddToCartButton().isDisplayed());

        productDetails.clickAddToCartButton();
        Thread.sleep(3000);
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();  // Accept the alert
            System.out.println("Add to Cart successful, alert accepted.");
        } else {
            Assert.fail("Add to Cart button is not functional or no success alert is displayed.");
        }
    }
    @Test(priority = 3)
    public void testProductDescriptionLength() throws InterruptedException {
    	productDetails.clickOnProduct();
        Thread.sleep(3000);
        String description = productDetails.getProductDescription();
        int wordCount = description.split("\\s+").length;
        System.out.println("Words Count of Description is: " + wordCount);
        Assert.assertTrue(wordCount > 5, "Product description does not have more than 5 words.");
    }
    @Test(priority = 4)
    public void testNavigationBetweenPages() throws InterruptedException {
    	productDetails.clickOnProduct();
        Thread.sleep(3000);
        String productDetailsUrl = driver.getCurrentUrl();
        driver.navigate().back();
        Thread.sleep(1000); // Add a small wait for the page to load

        // Verify that we are back on the home page (a basic check)
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.demoblaze.com/", "Navigation back to home page failed.");

        // Navigate forward to the product details page
        driver.navigate().forward();
        Thread.sleep(1000); // Add a small wait for the page to load

        // Verify that we are back on the product details page
        Assert.assertEquals(driver.getCurrentUrl(), productDetailsUrl, "Navigation forward to product details page failed.");

        // Verify that product details are still displayed (basic check)
        Assert.assertTrue(!productDetails.getProductName().isEmpty(), "Product name is missing after navigation.");
    }
    @Test(priority = 5)
    public void testProductPriceGreaterThanZero() throws InterruptedException {
    	productDetails.clickOnProduct();
        Thread.sleep(3000);
        String priceText = productDetails.getProductPrice();
        // Extract the numerical part of the price
        String priceString = priceText.replaceAll("[^0-9]", "");
        int price = Integer.parseInt(priceString);
        Assert.assertTrue(price > 0, "Product price is not greater than 0.");
        System.out.println(price > 0 ? "Product Price is Greater than 0" : "Product price is not greater than 0.");
    }
    @Test(priority = 7)
    public void testProductDescriptionExceedsCharacterLimit() throws InterruptedException {
    	productDetails.clickOnProduct();
        Thread.sleep(3000);
        String description = productDetails.getProductDescription();
        System.out.println(description.length());
        Assert.assertTrue(description.length() <= 200, "Product description exceeds the character limit of 100.");
    }

}