Feature: Sign Up Functionality

  Scenario Outline: Successful sign up
    Given I navigate to the Sign Up modal
    When I enter "<username>" and "<password>"
    And I click the Sign Up button
    Then I should see a confirmation message containing "Sign up successful."

    Examples:
      | username       | password |
      | testuser25204  | 1234     |
      | testuser25205  | 1234     |

  Scenario Outline: Sign up with existing username
    Given I navigate to the Sign Up modal
    When I enter "<username>" and "<password>"
    And I click the Sign Up button
    Then I should see a confirmation message containing "This user already exist."

    Examples:
      | username     | password |
      | testuser1    | 1234     |
      | testuser181  | 1234     |

  Scenario Outline: Sign up with short password
    Given I navigate to the Sign Up modal
    When I enter "<username>" and "<password>"
    And I click the Sign Up button
    Then I should see a confirmation message containing "Sign up successful."

    Examples:
      | username       | password |
      | testuser25206  | 1        |
      | testuser25207  | 12       |

  Scenario: Sign up with empty credentials
    Given I navigate to the Sign Up modal
    When I click the Sign Up button without entering any credentials
    Then I should see a confirmation message containing "Please fill out Username and Password."

  Scenario Outline: Sign up with minimum length credentials
    Given I navigate to the Sign Up modal
    When I enter "<username>" and "<password>"
    And I click the Sign Up button
    Then I should see a confirmation message containing "Sign up successful."

    Examples:
      | username | password |
      | @d@      | @d@      |
      | @e@      | @e@      |

  Scenario Outline: Sign up with maximum length credentials
    Given I navigate to the Sign Up modal
    When I enter "<username>" and "<password>"
    And I click the Sign Up button
    Then I should see a confirmation message containing "Sign up successful."

    Examples:
      | username                                                         | password                                                       |
      | qwertyuiopasdfghjklzxcvbnm@25204                                 | qwertyuiopasdfghjklzxcvbnm@25204                               |
      | qwertyuiopasdfghjklzxcvbnm@25205qwertyuiopasdfghjklzxcvbnm@25205 | qwertyuiopasdfghjklzxcvbnm@181qwertyuiopasdfghjklzxcvbnm@25205 |