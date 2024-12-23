package tests;

import java.time.Duration;

//import data.SignUpData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SignUpPage;

public class SignUpPageTest {

    WebDriver driver;
    SignUpPage signUpPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
        signUpPage = new SignUpPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "validSignUpData", priority = 1)
    public void testSuccessfulSignUp(String username, String password) {
        signUpPage.navigateToSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String confirmationMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Sign up successful."), "Confirmation message is incorrect.");
    }

    @Test(dataProvider = "duplicateUsernameData", priority = 2)
    public void testSignUpWithExistingUsername(String username, String password) {
        signUpPage.navigateToSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String confirmationMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("This user already exist."), "Incorrect error message for existing username.");
    }

    @Test(dataProvider = "shortPasswordData", priority = 3)
    public void testSignUpWithShortPassword(String username, String password) {
        signUpPage.navigateToSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String confirmationMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Password is too short"), "Incorrect error message for short password.");
    }

    @Test(priority = 4)
    public void testSignUpWithEmptyCredentials() {
        signUpPage.navigateToSignUpModal();
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String confirmationMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Please fill out Username and Password."), "Incorrect error message for empty credentials.");
    }

    @Test(dataProvider = "minLengthData", priority = 5)
    public void testSignUpWithMinLengthCredentials(String username, String password) {
        signUpPage.navigateToSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String confirmationMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Username and Password is too short."), "Confirmation message is incorrect for min length.");
    }

    @Test(dataProvider = "maxLengthData", priority = 6)
    public void testSignUpWithMaxLengthCredentials(String username, String password) {
        signUpPage.navigateToSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String confirmationMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Username and Password is too Long."), "Incorrect error message for max length.");
    }

    @DataProvider(name = "validSignUpData")
    public Object[][] validSignUpData() {
        return new Object[][]{
                {"testuser115", "1234"},
                {"testuser116", "1234"}
        };
    }

    @DataProvider(name = "duplicateUsernameData")
    public Object[][] duplicateUsernameData() {
        return new Object[][]{
                {"testuser1", "1234"},
                {"testuser181", "1234"}
        };
    }

    @DataProvider(name = "shortPasswordData")
    public Object[][] shortPasswordData() {
        return new Object[][]{
                {"testuser117", "1"},
                {"testuser118", "12"}
        };
    }

    @DataProvider(name = "minLengthData")
    public Object[][] minLengthData() {
        return new Object[][]{
                {"@b@", "@b@"},
                {"@c@", "@c@"}
        };
    }

    @DataProvider(name = "maxLengthData")
    public Object[][] maxLengthData() {
        return new Object[][]{
                {"qwertyuiopasdfghjklzxcvbnm@181", "qwertyuiopasdfghjklzxcvbnm@181"},
                {"qwertyuiopasdfghjklzxcvbnm@181qwertyuiopasdfghjklzxcvbnm@181", "qwertyuiopasdfghjklzxcvbnm@181qwertyuiopasdfghjklzxcvbnm@181"}
        };
    }
}