package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import java.time.Duration;

public class HomePageTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // DemoBlaze URL
        driver.get("https://www.demoblaze.com/");

        // HomePage object
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testHomeLink() throws InterruptedException {
        homePage.clickHome();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.demoblaze.com/index.html", "Home link failed");
        System.out.println("Test 1 Passed");
    }

    @Test(priority = 2)
    public void testContactLink() throws InterruptedException {
        homePage.clickContact();
        Thread.sleep(3000);
        Assert.assertTrue(homePage.isContactFormDisplayed(), "Contact form did not appear");
        System.out.println("Test 2 Passed");
    }

    @Test(priority = 3)
    public void testAboutLink() throws InterruptedException {
        homePage.clickAbout();
        Thread.sleep(3000);
        Assert.assertTrue(homePage.isVideoPopUpDisplayed(), "Video pop-up did not appear");
        System.out.println("Test 3 Passed");
    }

    @Test(priority = 4)
    public void testCartLink() throws InterruptedException {
        homePage.clickCart();
        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart page did not open");
        System.out.println("Test 4 Passed");
    }

    @Test(priority = 5)
    public void testLoginLink() throws InterruptedException {
        homePage.clickLogin();
        Thread.sleep(3000);
        // Wait and check if the login form pop-up is displayed
        Assert.assertTrue(homePage.isLoginFormDisplayed(), "Login form did not appear");
        System.out.println("Test 5 Passed");
    }

    @Test(priority = 6)
    public void testSignUpLink() throws InterruptedException {
        homePage.clickSignUp();
        Thread.sleep(3000);
        Assert.assertTrue(homePage.isSignUpFormDisplayed(), "Sign-up form did not appear");
        System.out.println("Test 6 Passed");
    }
    
    @Test(priority = 7)
    public void testPhonesCategory() throws InterruptedException {
    	homePage.clickPhonesCategory();
    	Thread.sleep(3000);
    	Assert.assertTrue(driver.getCurrentUrl().contains("#"), "Phone Category page did not open");
    	System.out.println("Test 7 Passed");
    }
    
    @Test(priority = 8)
    public void testLaptopCategory() throws InterruptedException {
    	homePage.clickLaptopCategory();
    	Thread.sleep(3000);
    	Assert.assertTrue(driver.getCurrentUrl().contains("#"), "Laptop Category page did not open");
    	System.out.println("Test 8 Passed");
    }
    
    @Test(priority = 9)
    public void testDesktopCategory() throws InterruptedException {
    	homePage.clickDesktopCategory();
    	Thread.sleep(3000);
    	Assert.assertTrue(driver.getCurrentUrl().contains("#"), "Desktop Category page did not open");
    	System.out.println("Test 9 Passed");
    }
  

    @Test(priority = 10)
    public void testClickAllProducts() throws Exception {
        try {
            boolean hasMorePages = true;
            int pageCount = 1;

            while (hasMorePages) {
                System.out.println("Processing page " + pageCount);
                
                // Get the number of products on current page
                int productCount = homePage.getProductCount();
                System.out.println("Found " + productCount + " products on page " + pageCount);
                
                if (productCount == 0) {
                    System.out.println("No products found on page " + pageCount);
                    break;
                }
                
                // Click each product on the current page
                for (int i = 0; i < productCount; i++) {
                    // Verify product is displayed
                    Assert.assertTrue(homePage.isProductDisplayed(i), 
                        "Product at index " + i + " is not displayed");
                    
                    // Get and print product name before clicking
                    String productName = homePage.getProductName(i);
                    System.out.println("Clicking product: " + productName);
                    
                    // Click the product
                    homePage.clickOnProduct(i);
                    Thread.sleep(3000);
                }

                // Check if there's a next page
                if (homePage.isNextButtonVisible()) {
                    homePage.clickNextButton();
                    Thread.sleep(3000);
                    pageCount++;
                } else {
                    hasMorePages = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            throw e;
        }
        System.out.println("Test 10 Passed");
    }
    @Test(priority = 11)
    public void testMainNavigationMenu() {
        // Verify the main navigation menu is accessible and functional
        Assert.assertTrue(driver.findElement(homePage.homeLink).isDisplayed(), "Home link is not displayed");
        Assert.assertTrue(driver.findElement(homePage.contactLink).isDisplayed(), "Contact link is not displayed");
        Assert.assertTrue(driver.findElement(homePage.aboutLink).isDisplayed(), "About link is not displayed");
        Assert.assertTrue(driver.findElement(homePage.cartLink).isDisplayed(), "Cart link is not displayed");
        System.out.println("Test 11 Passed");
    }
    @Test(priority = 12)
    public void testInteractiveElements() {
        // Verify that all interactive elements work correctly in all browsers
        Assert.assertTrue(driver.findElement(homePage.homeLink).isEnabled(), "Home link is not enabled");
        Assert.assertTrue(driver.findElement(homePage.contactLink).isEnabled(), "Contact link is not enabled");
        System.out.println("Test 12 Passed");
    }
    @SuppressWarnings("deprecation")
	@Test(priority = 13)
    public void testNoContentOverflowMobile() {
        // Test that no content overflows on mobile screens
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667)); // iPhone 6 size
        Assert.assertFalse(driver.findElement(homePage.productList).getAttribute("style").contains("overflow"), "Content overflows on mobile");
        System.out.println("Test 13 Passed");
    }
    @Test(priority = 14)
    public void testMobileButtonClickability() {
        // Verify mobile users can click all buttons and links easily
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667)); // iPhone 6 size
        Assert.assertTrue(driver.findElement(homePage.contactLink).isEnabled(), "Contact link is not clickable on mobile");
        System.out.println("Test 14 Passed");
    }
    @Test(priority = 15)
    public void testJavaScriptFunctions() {
        // Ensure that JavaScript functions like form validation work properly
        homePage.clickContact();
        Assert.assertTrue(homePage.isContactFormDisplayed(), "JavaScript form functionality failed");
        System.out.println("Test 15 Passed");
    }

    @Test(priority = 16)
    public void testMobileSmallScreen() {
        // Test the Web site on a mobile device with a small screen
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(320, 480)); // Small screen size
        Assert.assertTrue(driver.findElement(homePage.homeLink).isDisplayed(), "Home link is not displayed on small screen");
        System.out.println("Test 16 Passed");
    }

    @Test(priority = 17)
    public void testMobileOrientation() {
        // Test on different screen orientations (portrait vs landscape)
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667)); // Portrait
        Assert.assertTrue(driver.findElement(homePage.homeLink).isDisplayed(), "Home link not displayed in portrait");

        driver.manage().window().setSize(new org.openqa.selenium.Dimension(667, 375)); // Landscape
        Assert.assertTrue(driver.findElement(homePage.homeLink).isDisplayed(), "Home link not displayed in landscape");
        System.out.println("Test 17 Passed");
    }

    @Test(priority = 18)
    public void testMobileContentNotCutOff() {
        // Verify no content is cut off on mobile in portrait mode
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667)); // Portrait
        Assert.assertTrue(driver.findElement(homePage.productList).isDisplayed(), "Content is cut off on mobile");
        System.out.println("Test 18 Passed");
    }

    @Test(priority = 19)
    public void testClickableElementsMobile() {
        // Check that Click able elements are within easy reach on mobile devices
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667)); // iPhone 6 size
        Assert.assertTrue(driver.findElement(homePage.homeLink).isDisplayed(), "Home link not easily accessible on mobile");
        System.out.println("Test 19 Passed");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
