//package pages;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class ContactPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    By contactLink = By.xpath("//a[normalize-space()='Contact']");
//    By emailField = By.xpath("//input[@id='recipient-email']");
//    By nameField = By.xpath("//input[@id='recipient-name']");
//    By messageField = By.xpath("//textarea[@id='message-text']");
//    By sendMessageButton = By.xpath("//button[normalize-space()='Send message']");
//    By contactModal = By.id("contactModal"); // Modal ID
//    By overlay = By.xpath("//div[contains(@class,'overlay')]"); // Modify if needed
//
//    public ContactPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void navigateToContactModal() {
//        driver.findElement(contactLink).click();
//        System.out.println("Contact link clicked, waiting for modal visibility...");
//        
//        // Wait for the overlay to disappear (if any)
//        try {
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
//        } catch (Exception e) {
//            System.out.println("No overlay found, continuing...");
//        }
//
//        // Wait for the modal to be visible
//        wait.until(ExpectedConditions.visibilityOfElementLocated(contactModal));
//        System.out.println("Modal is visible now.");
//
//        // Scroll the modal into view (if needed)
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(contactModal));
//
//        // Ensure form fields are clickable
//        wait.until(ExpectedConditions.elementToBeClickable(emailField));
//        wait.until(ExpectedConditions.elementToBeClickable(nameField));
//        wait.until(ExpectedConditions.elementToBeClickable(messageField));
//    }
//
//    public void enterEmail(String email) {
//        WebElement emailFieldElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
//        emailFieldElement.click();
//        emailFieldElement.clear();
//        emailFieldElement.sendKeys(email);
//    }
//
//    public void enterContactName(String name) {
//        WebElement nameFieldElement = wait.until(ExpectedConditions.elementToBeClickable(nameField));
//        nameFieldElement.clear();
//        nameFieldElement.sendKeys(name);
//    }
//
//    public void enterMessage(String message) {
//        WebElement messageFieldElement = wait.until(ExpectedConditions.elementToBeClickable(messageField));
//        messageFieldElement.clear();
//        messageFieldElement.sendKeys(message);
//    }
//
//    public void clickSendMessageButton() {
//        driver.findElement(sendMessageButton).click();
//    }
//
//    public String getConfirmationMessage() {
//        wait.until(ExpectedConditions.alertIsPresent());
//        Alert alert = driver.switchTo().alert();
//        String alertText = alert.getText();
//        alert.accept(); // Or alert.dismiss() if needed
//        return alertText;
//    }
//
//    public boolean isConfirmationMessagePresent() {
//        try {
//            wait.until(ExpectedConditions.alertIsPresent());
//            return true;
//        } catch (org.openqa.selenium.TimeoutException e) {
//            return false;
//        }
//    }
//}

package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By contactLink = By.cssSelector("a[data-target='#exampleModal']");
    By emailField = By.id("recipient-email");
    By nameField = By.id("recipient-name");
    By messageField = By.id("message-text");
    By sendButton = By.cssSelector("button[onclick='send()']");

    // Constructor
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods
    public void clickContactPage() {
        wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
    }

    public void typeUserEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void typeUsername(String username) {
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameElement.clear();
        nameElement.sendKeys(username);
    }

    public void typeMessage(String message) {
        WebElement messageElement = wait.until(ExpectedConditions.elementToBeClickable(messageField));
        messageElement.clear();
        messageElement.sendKeys(message);
    }

    public void clickSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
}

