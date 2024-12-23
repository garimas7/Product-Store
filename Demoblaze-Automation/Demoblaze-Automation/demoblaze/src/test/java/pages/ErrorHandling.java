package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ErrorHandling {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    By loginLink = By.xpath("//a[@id='login2']");
    By usernameField = By.xpath("//input[@id='loginusername']");
    By passwordField = By.xpath("//input[@id='loginpassword']");
    By loginButton = By.xpath("//button[contains(text(),'Log in')]");
    By contactLink = By.xpath("//a[contains(text(),'Contact')]");
    By contactEmail = By.xpath("//input[@id='recipient-email']");
    By contactName = By.xpath("//input[@id='recipient-name']");
    By contactMessage = By.xpath("//textarea[@id='message-text']");
    By sendMessageButton = By.xpath("//button[normalize-space()='Send message']");
    By cartLink = By.xpath("//a[@id='cartur']");
    By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
    By nameField = By.xpath("//input[@id='name']");
    By countryField = By.xpath("//input[@id='country']");
    By cityField = By.xpath("//input[@id='city']");
    By cardField = By.xpath("//input[@id='card']");
    By monthField = By.xpath("//input[@id='month']");
    By yearField = By.xpath("//input[@id='year']");
    By purchaseButton = By.xpath("//button[contains(text(),'Purchase')]");

    public ErrorHandling(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean checkNonExistentPage() {
        driver.navigate().to("https://www.demoblaze.com/nonexistent-page");
        return driver.getPageSource().contains("404") || driver.getPageSource().contains("Page Not Found");
    }

    public void loginWithEmptyFields() {
        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void submitInvalidContactForm() {
        driver.findElement(contactLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmail));
        driver.findElement(contactEmail).sendKeys("invalid-email");
        driver.findElement(contactName).sendKeys("Test Name");
        driver.findElement(contactMessage).sendKeys("Test Message");
        driver.findElement(sendMessageButton).click();
    }

    public void proceedToCheckoutWithoutDetails() {
        driver.findElement(cartLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        driver.findElement(placeOrderButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseButton));
        driver.findElement(purchaseButton).click();
    }

    public void fillFormWithMaxInput() {
        String maxInput = "a".repeat(50);
        driver.findElement(contactLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactName));
        driver.findElement(contactName).sendKeys(maxInput);
    }

    public void fillFormWithMinInput() {
        driver.findElement(contactLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactName));
        driver.findElement(contactName).sendKeys("");
        driver.findElement(sendMessageButton).click();
    }

    public String waitForAlertAndAccept() {
        // Use the WebDriverWait with a Duration to handle alerts efficiently
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();
        alert.accept();
        return alertMessage;
    }
}
