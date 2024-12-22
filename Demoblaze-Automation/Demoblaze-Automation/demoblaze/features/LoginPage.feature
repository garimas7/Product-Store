Feature: Login functionality on the website

  Scenario Outline: Successful login
    Given I navigate to the login modal
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see the welcome message containing "<username>"

    Examples:
      | username         | password |
      | testuser181      | 1234     |
      | testuser25177    | 1234     |

  Scenario: Login with invalid username
    Given I navigate to the login modal
    When I enter username "testuser251777" and password "1234"
    And I click the login button
    Then I should see an alert with the message "User does not exist."

  Scenario: Login with invalid password
    Given I navigate to the login modal
    When I enter username "testuser181" and password "1233"
    And I click the login button
    Then I should see an alert with the message "Wrong password."

  Scenario: Login with empty credentials
    Given I navigate to the login modal
    When I click the login button
    Then I should see an alert with the message "Please fill out Username and Password."

  Scenario: Login with empty username
    Given I navigate to the login modal
    When I enter password "password123"
    And I click the login button
    Then I should see an alert with the message "Please fill out Username and Password."
    And I should not see the welcome message

  Scenario: Login with empty password
    Given I navigate to the login modal
    When I enter username "testuser"
    And I click the login button
    Then I should see an alert with the message "Please fill out Username and Password."
    And I should not see the welcome message
