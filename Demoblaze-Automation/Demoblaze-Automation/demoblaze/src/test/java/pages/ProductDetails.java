// ProductDetails.java (Object class)
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetails {
    WebDriver driver;
    WebDriverWait wait;

    // Locators for Product Details page
    By productLink = By.xpath("//div[@id='tbodyid']//div[1]//div[1]//a[1]//img[1]");
    By productNameLocator = By.xpath("//h2[normalize-space()='Samsung galaxy s6']");
    By productPriceLocator = By.xpath("//h3[@class='price-container']");
    By productDescriptionLocator = By.xpath("//p[contains(text(),'The Samsung Galaxy S6 is powered by 1.5GHz octa-co')]");
    By productImageLocator = By.xpath("//div[@class='item active']//img");
    By addToCartButtonLocator = By.xpath("//a[normalize-space()='Add to cart']");

    public ProductDetails(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnProduct() {
    	wait.until(ExpectedConditions.elementToBeClickable(productLink));
    	driver.findElement(productLink).click();
    }
    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));
        return driver.findElement(productNameLocator).getText();
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));
        return driver.findElement(productPriceLocator).getText();
    }

    public String getProductDescription() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productDescriptionLocator));
        return driver.findElement(productDescriptionLocator).getText();
    }

    public WebElement getProductImage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productImageLocator));
        return driver.findElement(productImageLocator);
    }

    public WebElement getAddToCartButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));
        return driver.findElement(addToCartButtonLocator);
    }

    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonLocator));
        driver.findElement(addToCartButtonLocator).click();
    }
}