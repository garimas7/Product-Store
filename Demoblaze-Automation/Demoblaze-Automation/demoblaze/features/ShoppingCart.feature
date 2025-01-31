Feature: Shopping Cart Page Tests
  This feature contains scenarios to test various functionalities of the shopping cart page.

  Scenario: Test if the cart page loads successfully
    Given the user navigates to the shopping site
    When the user clicks on the cart button
    Then the cart page should load successfully

  Scenario: Validate the title and URL of the cart page
    Given the user navigates to the shopping site
    When the user clicks on the cart button
    Then the cart page title should be "STORE"
    And the cart page URL should contain "cart.html"

  Scenario: Test if the username is displayed in the navigation bar
    Given the user logs in with valid credentials
    Then the username "testuser25177" should be displayed in the navigation bar

  Scenario: Test removing a product from the cart
    Given the user adds a product to the cart
    When the user navigates to the cart page
    And the user removes the product from the cart
    Then the cart should be empty

  Scenario: Test login attempt with missing required fields
    Given the user opens the login page
    When the user tries to log in with a missing password
    Then an error message "Please fill out Username and Password." should be displayed

  Scenario: Test login attempt with an empty username
    Given the user opens the login page
    When the user tries to log in with an empty username
    Then an error message "Username is required" should be displayed

  Scenario: Test adding and removing a single item from the cart
    Given the user adds a product to the cart
    When the user navigates to the cart page
    And the user removes the product
    Then the cart should be empty

  Scenario: Test placing an order after adding a single item
    Given the user adds a product to the cart
    When the user navigates to the cart page
    And the user places an order
    Then the order confirmation message "Thank you for your purchase!" should be displayed

  Scenario: Test adding and removing a single item twice
    Given the user adds a product to the cart
    And the user removes the product from the cart
    And the user adds the product again
    When the user navigates to the cart page
    Then the cart should contain 1 item