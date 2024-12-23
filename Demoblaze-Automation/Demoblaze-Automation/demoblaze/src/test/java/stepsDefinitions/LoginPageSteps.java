package stepsDefinitions;

import io.cucumber.java.en.*;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;

public class LoginPageSteps {

    WebDriver driver;
    LoginPage loginPage;
    String alertMessage;

    @Given("I navigate to the login modal")
    public void navigateToLoginModal() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginModal();
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I enter username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should see the welcome message containing {string}")
    public void verifyWelcomeMessage(String username) {
        Assert.assertTrue(loginPage.isWelcomeMessageVisible(), "Welcome message should be visible.");
        Assert.assertTrue(loginPage.getWelcomeMessage().contains(username), "Welcome message should contain the username.");
        driver.quit();
    }

    @Then("^I should see an alert with the message \"([^\"]*)\"$")
    public void verifyAlertMessage(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertNotNull(alert, "Alert should appear.");
		String actualMessage = alert.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Alert message does not match.");
		alert.accept();
		System.out.println("Verified standard JavaScript alert with message: " + actualMessage);
    }

    @Then("I should not see the welcome message on the login page")
    public void shouldNotSeeWelcomeMessageOnLoginPage() {
        Assert.assertFalse(loginPage.isWelcomeMessageVisible(), "Welcome message should not be visible on the login page.");
    }


    private String handleAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            return null;
        }
    }
}
