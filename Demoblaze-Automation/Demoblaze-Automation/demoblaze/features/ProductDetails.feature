Feature: Product Details Tests

  Scenario: Verify Product Details
    Given I am logged into the application
    When I click on a product
    Then the product name should not be empty
    And the product price should contain "$"
    And the product description should contain "Samsung Galaxy S6"
    And the product image should be visible

  Scenario: Verify Add to Cart Button is Visible and Functional
    Given I am logged into the application
    When I click on a product
    Then the Add to Cart button should be visible
    When I click on the Add to Cart button
    Then I should see a success alert

  Scenario: Verify Product Description Length
    Given I am logged into the application
    When I click on a product
    Then the product description should have more than 5 words

  Scenario: Verify Product Price Greater Than Zero
    Given I am logged into the application
    When I click on a product
    Then the product price should be greater than zero

  Scenario: Verify Product Description Character Limit
    Given I am logged into the application
    When I click on a product
    Then the product description should not exceed 200 characters
