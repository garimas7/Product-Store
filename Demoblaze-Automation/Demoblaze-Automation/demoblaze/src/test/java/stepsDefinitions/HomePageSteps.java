package stepsDefinitions;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {

    WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver.get("https://www.demoblaze.com/");
    }

    @When("I click on the Home link")
    public void i_click_on_the_home_link() {
        driver.findElement(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")).click();
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirect_to_the_homepage() {
        driver.findElement(By.xpath("(//a[normalize-space()='Phones'])[1]")).isDisplayed();
    }

    @When("I click on the Contact link")
    public void i_click_on_the_contact_link() {
        driver.findElement(By.xpath("//a[normalize-space()='Contact']")).click();
    }

    @Then("the Contact form should be displayed")
    public void the_contact_form_should_be_displayed() {
        driver.findElement(By.xpath("//div[@id='exampleModal']//div[@class='modal-body']")).isDisplayed();
    }

    @When("I click on the About link")
    public void i_click_on_the_about_link() {
        driver.findElement(By.xpath("//a[normalize-space()='About us']")).click();
    }

    @Then("the Video Pop-up should be displayed")
    public void the_video_pop_up_should_be_displayed() {
        driver.findElement(By.xpath("//div[@class='vjs-poster']")).isDisplayed();
    }

    @When("I click on the Cart link")
    public void i_click_on_the_cart_link() {
        driver.findElement(By.xpath("//a[@id='cartur']")).click();
    }

    @Then("I should be redirected to the cart page")
    public void i_should_be_redirected_to_the_cart_page() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart"), "Not redirected to the cart page");
    }

    @When("I click on the Login link")
    public void i_click_on_the_login_link() {
        driver.findElement(By.xpath("//a[@id='login2']")).click();
    }

    @Then("the Login form should be displayed")
    public void the_login_form_should_be_displayed() {
    	driver.findElement(By.xpath("//div[@id='logInModal']//div[@class='modal-body']")).isDisplayed();
    }

    @When("I click on the Sign Up link")
    public void i_click_on_the_sign_up_link() {
        driver.findElement(By.xpath("//a[@id='signin2']")).click();
    }

    @Then("the Sign Up form should be displayed")
    public void the_sign_up_form_should_be_displayed() {
    	driver.findElement(By.xpath("//div[@id='signInModal']//div[@class='modal-body']")).isDisplayed();
    }

    @When("I click on the Phones category")
    public void i_click_on_the_phones_category() {
        driver.findElement(By.xpath("(//a[normalize-space()='Phones'])[1]")).click();
    }

    @Then("I should be redirected to the Phones category page")
    public void i_should_be_redirected_to_the_phones_category_page() {
    	driver.findElement(By.xpath("//a[normalize-space()='Samsung galaxy s6']")).click();
    	WebElement productname = driver.findElement(By.tagName("h2"));
    	WebElement productDescription = driver.findElement(By.cssSelector("div[id='more-information'] p"));
    	Assert.assertTrue(productname.isDisplayed(), "Product Name not displayed");
    	Assert.assertTrue(productDescription.isDisplayed(), "Product Description not displayed");
    }

    @When("I click on the Laptop category")
    public void i_click_on_the_laptop_category() {
        driver.findElement(By.xpath("(//a[normalize-space()='Laptops'])[1]")).click();
    }

    @Then("I should be redirected to the Laptop category page")
    public void i_should_be_redirected_to_the_laptop_category_page() {
    	driver.findElement(By.xpath("//a[normalize-space()='Sony vaio i5']")).click();
    	WebElement productname = driver.findElement(By.tagName("h2"));
    	WebElement productDescription = driver.findElement(By.cssSelector("div[id='more-information'] p"));
    	Assert.assertTrue(productname.isDisplayed(), "Product Name not displayed");
    	Assert.assertTrue(productDescription.isDisplayed(), "Product Description not displayed");
    }

    @When("I click on the Desktop category")
    public void i_click_on_the_desktop_category() {
        driver.findElement(By.xpath("(//a[normalize-space()='Monitors'])[1]")).click();
    }

    @Then("I should be redirected to the Desktop category page")
    public void i_should_be_redirected_to_the_desktop_category_page() {
    	driver.findElement(By.xpath("//a[normalize-space()='Apple monitor 24']")).click();
    	WebElement productname = driver.findElement(By.tagName("h2"));
    	WebElement productDescription = driver.findElement(By.cssSelector("div[id='more-information'] p"));
    	Assert.assertTrue(productname.isDisplayed(), "Product Name not displayed");
    	Assert.assertTrue(productDescription.isDisplayed(), "Product Description not displayed");
    }

    @When("I click on any product")
    public void i_click_on_any_product() {
        driver.findElement(By.xpath("//a[normalize-space()='Nokia lumia 1520']")).click();
    }

    @Then("I should be able to view the product details")
    public void i_should_be_able_to_view_the_product_details() {
    	WebElement productname = driver.findElement(By.tagName("h2"));
    	WebElement productDescription = driver.findElement(By.cssSelector("div[id='more-information'] p"));
    	Assert.assertTrue(productname.isDisplayed(), "Product Name not displayed");
    	Assert.assertTrue(productDescription.isDisplayed(), "Product Description not displayed");
    }
    
    @When("I click on all products")
    public void i_click_on_all_products() throws InterruptedException {
        boolean hasNextPage = true;
        int currentPage = 1;  // Track current page number
        Set<String> processedProducts = new HashSet<>();  // Track processed products
        
        while (hasNextPage && currentPage <= 2) {  // Limit to 2 pages
            System.out.println("Processing Page: " + currentPage);
            
            // Wait for products to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tbodyid .card a")));
            
            // Get all products on current page
            List<WebElement> products = driver.findElements(By.cssSelector("#tbodyid .card a"));
            
            if (products.isEmpty()) {
                System.out.println("No products found on page " + currentPage);
                break;
            }
            
            for (WebElement product : products) {
                try {
                    String productName = product.getText();
                    
                    // Skip if product already processed
                    if (processedProducts.contains(productName)) {
                        continue;
                    }
                    processedProducts.add(productName);
                    
                    System.out.println("Processing Product: " + productName + " on Page: " + currentPage);
                    
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
                    Thread.sleep(1000);  // Increased wait time
                    
                    product.click();
                    
                    // Updated wait and selector for product details
                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-content h2.name")));
                        WebElement productDetails = driver.findElement(By.cssSelector(".product-content h2.name"));
                        Assert.assertTrue(productDetails.isDisplayed(), "Product details not displayed for: " + productName);
                    } catch (Exception e) {
                        System.out.println("Could not find product details for: " + productName);
                    }
                    
                    driver.navigate().back();
                    Thread.sleep(1000);  // Wait after navigation
                    
                    // Wait for products to be visible again
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tbodyid .card a")));
                    
                } catch (Exception e) {
                    System.out.println("Error processing product: " + e.getMessage());
                }
            }
            
            // Check for next page only if we're on page 1
            if (currentPage == 1) {
                try {
                    WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("next2")));
                    if (nextButton.isEnabled()) {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].click();", nextButton);
                        Thread.sleep(2000);  // Increased wait time after page change
                        currentPage++;
                    } else {
                        hasNextPage = false;
                    }
                } catch (Exception e) {
                    System.out.println("No more pages available or error navigating: " + e.getMessage());
                    hasNextPage = false;
                }
            } else {
                // Exit loop after processing second page
                hasNextPage = false;
            }
        }
        System.out.println("Total products processed: " + processedProducts.size());
    }
}