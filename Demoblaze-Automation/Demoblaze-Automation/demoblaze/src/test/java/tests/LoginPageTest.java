package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;
    private String handleAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            alert.accept();
            return alertMessage;
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "validLoginData", priority = 1)
    public void testSuccessfulLogin(String username, String password) throws InterruptedException {
        loginPage.navigateToLoginModal();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        System.out.println(loginPage.getWelcomeMessage());
        Assert.assertTrue(loginPage.isWelcomeMessageVisible(), "Welcome message should be visible after successful login.");
        Assert.assertTrue(loginPage.getWelcomeMessage().contains(username), "Welcome message should contain the username.");
    }

    @Test(dataProvider = "invalidUsernameData", priority = 2)
    public void testLoginWithInvalidUsername(String username, String password) throws InterruptedException {
        loginPage.navigateToLoginModal();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isConfirmationMessagePresent(), "Confirmation message should be present for invalid username.");
        String confirmationMessage = loginPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("User does not exist."), "Incorrect error message for invalid username.");
    }

    @Test(dataProvider = "invalidPasswordData", priority = 3)
    public void testLoginWithInvalidPassword(String username, String password) throws InterruptedException {
        loginPage.navigateToLoginModal();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isConfirmationMessagePresent(), "Confirmation message should be present for invalid password.");
        String confirmationMessage = loginPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Wrong password."), "Incorrect error message for invalid password.");
    }

    @Test(priority = 4)
    public void testLoginWithEmptyCredentials() throws InterruptedException {
        loginPage.navigateToLoginModal();
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isConfirmationMessagePresent(), "Confirmation message should be present for empty credentials.");
        String confirmationMessage = loginPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Please fill out Username and Password."), "Incorrect error message for empty credentials.");
    }

    @Test(priority = 5)
    public void testLoginWithEmptyUsername() throws InterruptedException {
        loginPage.navigateToLoginModal();
        loginPage.enterPassword("password123");
        loginPage.clickLoginButton();
        String alertMessage = handleAlert(); // Handle the alert
        
        Assert.assertNotNull(alertMessage, "Alert should appear for empty username.");
        Assert.assertTrue(alertMessage.contains("Please fill out Username and Password."), 
            "Incorrect error message for empty username.");
        Assert.assertFalse(loginPage.isWelcomeMessageVisible(), 
            "Welcome message should not be visible.");
    }
    @Test(priority = 6)
    public void testLoginWithEmptyPassword() throws InterruptedException {
        loginPage.navigateToLoginModal();
        loginPage.enterUsername("testuser");
        loginPage.clickLoginButton();
        String alertMessage = handleAlert(); // Handle the alert
        
        Assert.assertNotNull(alertMessage, "Alert should appear for empty password.");
        Assert.assertTrue(alertMessage.contains("Please fill out Username and Password."), 
            "Incorrect error message for empty password.");
        Assert.assertFalse(loginPage.isWelcomeMessageVisible(), 
            "Welcome message should not be visible.");
    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][]{
                {"testuser181", "1234"},
                {"testuser25177", "1234"}
        };
    }

    @DataProvider(name = "invalidUsernameData")
    public Object[][] invalidUsernameData() {
        return new Object[][]{
                {"testuser251777", "1234"}
        };
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() {
        return new Object[][]{
                {"testuser181", "1233"}
        };
    }
}