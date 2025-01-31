package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    public HomePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

	// Locators for navigation bar
    public By homeLink = By.xpath("//li[@class='nav-item active']//a[@class='nav-link']");
    public By contactLink = By.xpath("//a[normalize-space()='Contact']");
    public By aboutLink = By.xpath("//a[normalize-space()='About us']");
    public By cartLink = By.xpath("//a[@id='cartur']");
    By loginLink = By.xpath("//a[@id='login2']");
    By signUpLink = By.xpath("//a[@id='signin2']");

    By contactForm = By.xpath("(//div[@class='modal-body'])[1]");
    By videoPopUp = By.xpath("//div[@class='vjs-poster']");
    By loginForm = By.xpath("//div[@id='logInModal']//div[@class='modal-body']");
    By signUpForm = By.xpath("(//div[@class='modal-body'])[2]");
    
    final Duration TIMEOUT = Duration.ofSeconds(10);

    // Locators for category buttons
   	By phoneCategory = By.xpath("(//a[normalize-space()='Phones'])[1]");
   	By laptopCategory = By.xpath("(//a[normalize-space()='Laptops'])[1]");
   	By desktopCategory = By.xpath("(//a[normalize-space()='Monitors'])[1]");

    public By productList = By.xpath("//div[@id='tbodyid']");
    
    
    By productLinks = By.xpath("//div[@class='card-block']//h4/a");
    By nextButton = By.xpath("//button[@id='next2']");
    By productContainer = By.xpath("//div[@id='tbodyid']");
    By productName = By.xpath("//h2[@class='name']");
    
    // Methods for interacting with navbar
    public void clickHome() {
        driver.findElement(homeLink).click();
    }

    public void clickContact() {
        driver.findElement(contactLink).click();
    }

    public void clickAbout() {
        driver.findElement(aboutLink).click();
    }

    public void clickCart() {
        driver.findElement(cartLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickSignUp() {
        driver.findElement(signUpLink).click();
    }

    // Methods for interacting with category buttons
    public void clickPhonesCategory() {
        driver.findElement(phoneCategory).click();
    }

    public void clickLaptopCategory() {
        driver.findElement(laptopCategory).click();
    }

    public void clickDesktopCategory() {
        driver.findElement(desktopCategory).click();
    }

    // Method to verify product list is displayed
    public boolean isProductListDisplayed() {
        return driver.findElement(productList).isDisplayed();
    }
    
    public boolean isContactFormDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            WebElement contactFormElement = wait.until(ExpectedConditions.visibilityOfElementLocated(contactForm));
            return contactFormElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isVideoPopUpDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            WebElement videoPopUpElement = wait.until(ExpectedConditions.visibilityOfElementLocated(videoPopUp));
            return videoPopUpElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isLoginFormDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            WebElement loginFormElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            return loginFormElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isSignUpFormDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            WebElement signUpFormElement = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpForm));
            return signUpFormElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickOnProduct(int index) throws Exception {
        try {
            Thread.sleep(1000); // Small delay to ensure page stability
            List<WebElement> products = driver.findElements(productLinks);
            
            if (index < products.size()) {
                @SuppressWarnings("unused")
				String productTitle = products.get(index).getText();
                wait.until(ExpectedConditions.elementToBeClickable(products.get(index))).click();
                
                // Wait for product page to load
                wait.until(ExpectedConditions.presenceOfElementLocated(productName));
                
                // Navigate back to homepage
                driver.navigate().back();
                
                // Wait for products to be visible again
                wait.until(ExpectedConditions.presenceOfElementLocated(productLinks));
            }
        } catch (Exception e) {
            System.out.println("Error clicking product at index " + index + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean isNextButtonVisible() {
        try {
            Thread.sleep(1000); // Small delay to ensure page stability
            return driver.findElements(nextButton).size() > 0 && 
                   driver.findElement(nextButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNextButton() throws Exception {
        try {
            if (isNextButtonVisible()) {
                WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
                next.click();
                Thread.sleep(1000); // Wait for page transition
            }
        } catch (Exception e) {
            System.out.println("Error clicking next button: " + e.getMessage());
            throw e;
        }
    }

    public int getProductCount() {
        try {
            Thread.sleep(1000); // Small delay to ensure page stability
            List<WebElement> products = driver.findElements(productLinks);
            System.out.println("Found " + products.size() + " products");
            return products.size();
        } catch (Exception e) {
            System.out.println("Error getting product count: " + e.getMessage());
            return 0;
        }
    }

    public boolean isProductDisplayed(int index) {
        try {
            List<WebElement> products = driver.findElements(productLinks);
            return index < products.size() && products.get(index).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductName(int index) {
        try {
            List<WebElement> products = driver.findElements(productLinks);
            if (index < products.size()) {
                return products.get(index).getText();
            }
        } catch (Exception e) {
            System.out.println("Error getting product name at index " + index + ": " + e.getMessage());
        }
        return "";
    }
}
