Feature: Home Page Testing

  Scenario: Verify Home Link is working
		Given I am on the homepage
		When I click on the Home link
		Then I should be redirected to the homepage

	Scenario: Verify Contact Form is displayed
		Given I am on the homepage
		When I click on the Contact link
		Then the Contact form should be displayed

	Scenario: Verify About Us Video Pop-up
		Given I am on the homepage
		When I click on the About link
		Then the Video Pop-up should be displayed

	Scenario: Verify Cart Link works
		Given I am on the homepage
		When I click on the Cart link
		Then I should be redirected to the cart page

  Scenario: Verify Login Form appears
		Given I am on the homepage
		When I click on the Login link
		Then the Login form should be displayed

  Scenario: Verify Sign Up Form appears
    Given I am on the homepage
    When I click on the Sign Up link
    Then the Sign Up form should be displayed

  Scenario: Verify Phones Category works
    Given I am on the homepage
    When I click on the Phones category
    Then I should be redirected to the Phones category page

  Scenario: Verify Laptop Category works
    Given I am on the homepage
    When I click on the Laptop category
    Then I should be redirected to the Laptop category page

  Scenario: Verify Desktop Category works
    Given I am on the homepage
    When I click on the Desktop category
    Then I should be redirected to the Desktop category page

  Scenario: Verify product clicking works
    Given I am on the homepage
    When I click on any product
    Then I should be able to view the product details
    
  Scenario: Verify all products clicking works
  	Given I am on the homepage
  	When I click on all products
