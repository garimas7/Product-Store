package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {

    WebDriver driver;
    WebDriverWait wait;
    By signUpLink = By.xpath("//a[@id='signin2']");
    By usernameField = By.xpath("//input[@id='sign-username']");
    By passwordField = By.xpath("//input[@id='sign-password']");
    By signUpButton = By.xpath("//button[normalize-space()='Sign up']");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToSignUpModal() {
        driver.findElement(signUpLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal"))); // Wait for the modal to appear
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept(); // Or alert.dismiss() if needed
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
}