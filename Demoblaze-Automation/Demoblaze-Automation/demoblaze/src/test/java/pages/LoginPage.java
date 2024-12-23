package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By loginLink = By.xpath("//a[@id='login2']");
    By usernameField = By.xpath("//input[@id='loginusername']");
    By passwordField = By.xpath("//input[@id='loginpassword']");
    By loginButton = By.xpath("//button[normalize-space()='Log in']");
    By welcomeUser = By.xpath("//a[@id='nameofuser']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLoginModal() {
        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
    		
        driver.findElement(loginButton).click();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public boolean isConfirmationMessagePresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean isWelcomeMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
            return driver.findElement(welcomeUser).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
        return driver.findElement(welcomeUser).getText();
    }
}