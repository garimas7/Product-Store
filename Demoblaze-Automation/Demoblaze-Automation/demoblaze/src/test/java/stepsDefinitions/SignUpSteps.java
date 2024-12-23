package stepsDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignUpPage;
import org.testng.Assert;

public class SignUpSteps {

    WebDriver driver;
    SignUpPage signUpPage;

    @Given("I navigate to the Sign Up modal")
    public void navigateToSignUpModal() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        signUpPage = new SignUpPage(driver);
        signUpPage.navigateToSignUpModal();
    }

    @When("I enter {string} and {string}")
    public void enterCredentials(String username, String password) {
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
    }

    @When("I click the Sign Up button")
    public void clickSignUpButton() {
        signUpPage.clickSignUpButton();
    }

    @When("I click the Sign Up button without entering any credentials")
    public void clickSignUpWithoutCredentials() {
        signUpPage.clickSignUpButton();
    }

    @Then("I should see a confirmation message containing {string}")
    public void verifyConfirmationMessage(String expectedMessage) {
        Assert.assertTrue(signUpPage.isConfirmationMessagePresent(), "Confirmation message should be present.");
        String actualMessage = signUpPage.getConfirmationMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Confirmation message does not match.");
        driver.quit();
    }
}
