package stepsDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.ContactPage;

public class ContactPageSteps {

    private WebDriver driver;
    private ContactPage contactPage;
    private String alertText;

    @Given("I navigate to the Contact Page")
    public void iNavigateToTheContactPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        contactPage = new ContactPage(driver);
        contactPage.clickContactPage();
    }

    @When("I fill the contact form with {string}, {string}, and {string}")
    public void iFillTheContactForm(String email, String name, String message) {
        if (!email.isEmpty()) contactPage.typeUserEmail(email);
        if (!name.isEmpty()) contactPage.typeUsername(name);
        if (!message.isEmpty()) contactPage.typeMessage(message);
    }

    @When("I submit the form")
    public void iSubmitTheForm() {
        contactPage.clickSendButton();
        alertText = contactPage.getAlertText();
    }

    @Then("I should see a confirmation alert with {string}")
    public void iShouldSeeAConfirmationAlert(String expectedAlert) {
        Assert.assertTrue(alertText.contains(expectedAlert), "Alert message does not match.");
        tearDown();
    }

    @When("I submit the form without filling the fields")
    public void iSubmitTheFormWithoutFillingTheFields() {
        contactPage.clickSendButton();
        alertText = contactPage.getAlertText();
    }

    @Then("I should see a validation alert with {string}")
    public void iShouldSeeAValidationAlert(String expectedAlert) {
        Assert.assertTrue(alertText.contains(expectedAlert), "Validation message does not match.");
        tearDown();
    }

    private void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
