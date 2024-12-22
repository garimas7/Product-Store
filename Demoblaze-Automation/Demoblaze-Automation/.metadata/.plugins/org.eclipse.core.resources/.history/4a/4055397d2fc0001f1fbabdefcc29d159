package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ContactPage;

import java.time.Duration;

public class ContactPageTest {

    private WebDriver driver;
    private ContactPage contactPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
        contactPage = new ContactPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1, dataProvider = "validDetails")
    public void testSubmitValidDetails(String email, String name, String message) {
        contactPage.clickContactPage();
        contactPage.typeUserEmail(email);
        contactPage.typeUsername(name);
        contactPage.typeMessage(message);
        contactPage.clickSendButton();

        String alertText = contactPage.getAlertText();
        Assert.assertTrue(alertText.contains("Thanks for the message!!"), "Alert message does not match.");
    }


    @Test(priority = 2)
    public void testSubmitWithEmptyRequiredFields() {
        contactPage.clickContactPage();
        contactPage.clickSendButton();

        String alertText = contactPage.getAlertText();
        Assert.assertTrue(alertText.contains("Please fill out the required fields"), "Validation message does not match.");
    }

    @Test(priority = 3)
    public void testNameFieldMinLength() {
        contactPage.clickContactPage();
        contactPage.typeUserEmail("test@example.com");
        contactPage.typeUsername("AB"); // Less than 3 characters
        contactPage.typeMessage("Hello World");
        contactPage.clickSendButton();

        String alertText = contactPage.getAlertText();
        Assert.assertTrue(alertText.contains("Name must be at least 3 characters"), "Validation message does not match.");
    }

    @Test(priority = 4, dataProvider = "maxLengthData")
    public void testMessageMaxLength(String email, String name, String longMessage) {
        contactPage.clickContactPage();
        contactPage.typeUserEmail(email);
        contactPage.typeUsername(name);
        contactPage.typeMessage(longMessage);
        contactPage.clickSendButton();

        String alertText = contactPage.getAlertText();
        Assert.assertTrue(alertText.contains("Thanks for the message!!"), "Alert message does not match.");
    }

    @Test(priority = 5)
    public void testSubmitWithOnlyRequiredFields() {
        contactPage.clickContactPage();
        contactPage.typeUserEmail("test@example.com");
        contactPage.typeUsername("Valid Name");
        contactPage.clickSendButton();

        String alertText = contactPage.getAlertText();
        Assert.assertTrue(alertText.contains("Thanks for the message!!"), "Alert message does not match.");
    }

    @Test(priority = 6, dataProvider = "maxLengthData")
    public void testNameFieldMaxLength(String email, String maxLengthName, String message) {
        contactPage.clickContactPage();
        contactPage.typeUserEmail(email);
        contactPage.typeUsername(maxLengthName);
        contactPage.typeMessage(message);
        contactPage.clickSendButton();

        String alertText = contactPage.getAlertText();
        Assert.assertTrue(alertText.contains("Thanks for the message!!"), "Alert message does not match.");
    }

    // Data Providers
    @DataProvider(name = "validDetails")
    public Object[][] provideValidDetails() {
        return new Object[][]{
                {"validuser@example.com", "Valid User", "This is a valid message."}
        };
    }

    @DataProvider(name = "maxLengthData")
    public Object[][] provideMaxLengthData() {
        String longMessage = "A".repeat(1000); // Assuming 1000 is the max allowed length
        String maxLengthName = "A".repeat(50); // Assuming 50 is the max allowed length
        return new Object[][]{
                {"testuser@example.com", maxLengthName, longMessage}
        };
    }
}
