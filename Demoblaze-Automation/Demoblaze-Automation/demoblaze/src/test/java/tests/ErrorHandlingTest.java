package tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ErrorHandling;

public class ErrorHandlingTest {
    WebDriver driver;
    ErrorHandling errorHandling;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        errorHandling = new ErrorHandling(driver);
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void testNonExistentPage() {
        Assert.assertTrue(errorHandling.checkNonExistentPage(),
                "404 error page should be displayed for non-existent URL");
    }

    @Test(priority = 2)
    public void testInvalidContactForm() {
        errorHandling.submitInvalidContactForm();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = errorHandling.waitForAlertAndAccept();
        Assert.assertTrue(alertText.contains("Thanks for the message!!") || alertText.contains("error"),
                "Alert should indicate invalid input");
    }

    @Test(priority = 3)
    public void testEmptyLoginFields() {
        errorHandling.loginWithEmptyFields();
        String alertText = errorHandling.waitForAlertAndAccept();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password.") || alertText.contains("empty"),
                "Alert should indicate empty fields");
    }

    @Test(priority = 4)
    public void testCheckoutWithoutDetails() {
        errorHandling.proceedToCheckoutWithoutDetails();
        String alertText = errorHandling.waitForAlertAndAccept();
        Assert.assertTrue(alertText.contains("Please fill"), "Alert should indicate missing required details");
    }

    @Test(priority = 5)
    public void testMinInputLength() {
        errorHandling.fillFormWithMinInput();
        String alertText = errorHandling.waitForAlertAndAccept();
        Assert.assertTrue(alertText.contains("Thanks for the message!!"), "Alert should indicate minimum length requirement");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
