package pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ShoppingCartPage {

    WebDriver driver;
    WebDriverWait wait;

    public String handleAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            alert.accept();
            return alertMessage;
        } catch (NoAlertPresentException e) {
            return null;
        }
    }
    public void waitForAlertAndAccept() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent()); // Wait for the alert
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		System.out.println("Alert found with message: " + alertMessage);
		alert.accept(); // Accept the alert
    }
    // Locators
    By cartpage = By.xpath("//a[@id='cartur']");
   	By cartPageTitle = By.xpath("//h2[normalize-space()='Products']");
   	By cartTable = By.xpath("//tbody");
   	By cartTableRows = By.xpath("//tr[@class='success']");
   	By deleteButton = By.xpath("//a[normalize-space()='Delete']");
   	By placeOrderButton = By.xpath("//button[normalize-space()='Place Order']");
   	By navigationBarUsername = By.xpath("//a[@id='nameofuser']");
   	By loginLink = By.xpath("//a[@id='login2']"); // Login button in header
   	By loginModal = By.id("logInModal"); // Login modal
   	By loginusernameInput = By.id("loginusername");
   	By loginPasswordInput = By.id("loginpassword");
   	By loginSubmitButton = By.xpath("//button[text()='Log in']");
   	By selectCategory = By.xpath("(//a[normalize-space()='Monitors'])[1]");
   	By selectProduct = By.xpath("//img[@src='imgs/apple_cinema.jpg']");
   	By addToCart = By.xpath("//a[normalize-space()='Add to cart']");
   	By username = By.xpath("//input[@id='name']");
   	By usercountry = By.xpath("//input[@id='country']");
   	By usercity = By.xpath("//input[@id='city']");
   	By usercard = By.xpath("//input[@id='card']");
   	By month = By.xpath("//input[@id='month']");
   	By year = By.xpath("//input[@id='year']");
   	By placeOrderConfirm = By.xpath("//button[normalize-space()='Purchase']");

    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public boolean deleteButtonVisible() {
    	return driver.findElement(deleteButton).isDisplayed();
    }
    public void addAndRemoveProduct() {
    	driver.findElement(selectCategory).click();
    	driver.findElement(selectProduct).click();
    	driver.findElement(addToCart).click();
    	handleAlert();
    	driver.findElement(cartpage).click();
    	driver.findElement(deleteButton).click();
    }
    
    public void addProduct() {
    	driver.findElement(selectCategory).click();
    	driver.findElement(selectProduct).click();
    	driver.findElement(addToCart).click();
    	handleAlert();
    }
    public void clickCartButton() {
    	driver.findElement(cartpage).click();
    }
    public boolean isCartPageLoaded() {
        return driver.findElement(cartPageTitle).isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public boolean isUsernameDisplayed() {
        return driver.findElement(navigationBarUsername).isDisplayed();
    }

    public void deleteProductFromCart() {
        driver.findElement(deleteButton).click();
    }

    @SuppressWarnings("unlikely-arg-type")
	public boolean isProductRemoved() {
        return driver.findElements(cartTable).contains(cartTableRows);
    }
    public void clickPlaceOrder() {
    	driver.findElement(placeOrderButton).click();
    	driver.findElement(username).sendKeys("Garima");
    	driver.findElement(usercountry).sendKeys("India");
    	driver.findElement(usercity).sendKeys("Delhi");
    	driver.findElement(usercard).sendKeys("1234567890");
    	driver.findElement(month).sendKeys("05");
    	driver.findElement(year).sendKeys("2024");
    	driver.findElement(placeOrderConfirm).click();
    }

    public void navigateToLoginModal() {
        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
    }

    public void enterUsername(String username) {
        driver.findElement(loginusernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLoginButton() {
    		
        driver.findElement(loginSubmitButton).click();
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(navigationBarUsername));
            return driver.findElement(navigationBarUsername).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(navigationBarUsername));
        return driver.findElement(navigationBarUsername).getText();
    }

    public void clearCart() {
        List<WebElement> deleteButtons = driver.findElements(deleteButton);
        for (WebElement button : deleteButtons) {
            button.click();
        }
    }
	public void login(String username, String password) {
		driver.findElement(loginLink).click();
		driver.findElement(loginusernameInput).sendKeys(username);
		driver.findElement(loginPasswordInput).sendKeys(password);
		driver.findElement(loginSubmitButton).click();
	}
	// Check if a product is in the cart
	public boolean isProductInCart() {
	    List<WebElement> rows = driver.findElements(cartTableRows);
	    return !rows.isEmpty();  // If there are any rows in the cart, it contains products
	}

	// Check if the cart is empty
	public boolean isCartEmpty() {
	    List<WebElement> rows = driver.findElements(cartTableRows);
	    return rows.isEmpty();  // If there are no rows, the cart is empty
	}
}