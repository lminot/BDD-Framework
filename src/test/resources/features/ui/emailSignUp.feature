Feature: Verify medmen.com's newsletter email sign-up functionality

  @activeMonitor
  Scenario: Validate the Age-gate Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    When I click "YES" on the age verification prompt
    And I enter a valid email address into the "Age Gate Newsletter" sign up box
    Then I click the enter button
    And now my email is searchable in Clutch

  Scenario: Validate the "exit-pop" Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button
    When I scroll down the page
    And I enter a valid email address into the "Get 10% Off Your First Purchase." sign up box
    Then the "Thank you for signing up!" text is displayed
    And now my email is searchable in Clutch

  Scenario: Validate the landing page Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button
    When I scroll down the page
    And I enter a valid email address into the "Keep in Touch" sign up box
    Then the "Thank you for signing up!" text is displayed
    And now my email is searchable in Clutch







