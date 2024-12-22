Feature: Checkout Functionality

  Background:
    Given I am on the demoblaze website

  Scenario: Registered user successful checkout
    Given I am logged in as "testuser25177" with password "1234"
    When I add a product to cart
    And I navigate to cart
    And I click on place order
    And I fill checkout details with following information
      | name         | country       | city   | card       | month | year |
      | Garima Singh | India         | Delhi  | 1234567890 | 05    | 2024 |
    And I click on purchase button
    Then I should see successful purchase message

  Scenario: Guest user successful checkout
    When I add a product to cart
    And I navigate to cart
    And I click on place order
    And I fill checkout details with following information
      | name         | country        | city  | card       | month | year |
      | Garima Singh | India          | Delhi | 1234567890 | 05    | 2024 |
    And I click on purchase button
    Then I should see successful purchase message

  Scenario: Add multiple items to cart
    Given I am logged in as "testuser25177" with password "1234"
    When I add "10" products to cart
    And I navigate to cart
    Then I should see all items in the cart

  Scenario: Single item checkout
    When I add a product to cart
    And I navigate to cart
    Then cart should contain "1" item
    When I click on place order
    And I fill checkout details with following information
      | name         | country | city  | card       | month | year |
      | Garima Singh | India   | Delhi | 1234567890 | 06    | 2024 |
    And I click on purchase button
    Then I should see successful purchase message