Feature: Contact Page

  Background:
    Given I navigate to the Contact Page

  Scenario Outline: Submit valid details
    When I fill the contact form with "<email>", "<name>", and "<message>"
    And I submit the form
    Then I should see a confirmation alert with "Thanks for the message!!"

    Examples:
      | email                  | name       | message                    |
      | validuser@example.com  | Valid User | This is a valid message.   |

  Scenario: Submit with empty required fields
    When I submit the form without filling the fields
    Then I should see a validation alert with "Thanks for the message!!"

  Scenario: Name field minimum length validation
    When I fill the contact form with "test@example.com", "AB", and "Hello World"
    And I submit the form
    Then I should see a validation alert with "Thanks for the message!!"

  Scenario Outline: Message maximum length validation
    When I fill the contact form with "<email>", "<name>", and "<longMessage>"
    And I submit the form
    Then I should see a confirmation alert with "Thanks for the message!!"

    Examples:
      | email                  | name          | longMessage            |
      | testuser@example.com   | Valid User    | AAAAAAAAAAAAAAAAAAAAA  |

  Scenario: Submit with only required fields
    When I fill the contact form with "test@example.com", "Valid Name", and ""
    And I submit the form
    Then I should see a confirmation alert with "Thanks for the message!!"

  Scenario Outline: Name field maximum length validation
    When I fill the contact form with "<email>", "<maxLengthName>", and "<message>"
    And I submit the form
    Then I should see a confirmation alert with "Thanks for the message!!"

    Examples:
      | email                  | maxLengthName                         | message                  |
      | testuser@example.com   | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA | AAAAAAAAAAAAAAAAAAAAAA   |
