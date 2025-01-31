package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    
    // Locators for checkout page
    By cartButton = By.xpath("//a[@id='cartur']");
    By placeOrderButton = By.xpath("//button[normalize-space()='Place Order']");
    By nameField = By.xpath("//input[@id='name']");
    By countryField = By.xpath("//input[@id='country']");
    By cityField = By.xpath("//input[@id='city']");
    By cardField = By.xpath("//input[@id='card']");
    By monthField = By.xpath("//input[@id='month']");
    By yearField = By.xpath("//input[@id='year']");
    By purchaseButton = By.xpath("//button[normalize-space()='Purchase']");
    By successMessage = By.xpath("//h2[normalize-space()='Thank you for your purchase!']");
    By okButton = By.xpath("//button[normalize-space()='OK']");
    By deleteItemButton = By.xpath("//a[normalize-space()='Delete']");
    By cartItems = By.xpath("//tr[@class='success']");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to click on the cart button
    public void clickCartButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartButton)).click();
    }

    // Method to click on Place Order
    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    // Method to fill checkout details
    public void fillCheckoutDetails(String name, String country, String city, String card, String month, String year) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
    }

    // Method to click Purchase button
    public void clickPurchaseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
    }

    // Method to verify success message
    public boolean isPurchaseSuccessful() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    // Method to close the success modal
    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }

    // Method to get the number of items in the cart
    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    // Method to delete items from the cart
    public void deleteCartItem() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteItemButton)).click();
    }
}