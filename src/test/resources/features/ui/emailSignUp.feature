Feature: Verify medmen.com's newsletter email sign-up functionality

  @activeMonitor1
  Scenario: Validate the Newsletter Email Signup API functionality with a valid email
    Given I navigate to the Medmen homepage
    When I click "YES" on the age verification prompt
    And I enter a valid email address into the Newsletter sign up box
#    And I click the enter button
#    Then my email is now signed up for the Medmen Newsletter via Clutch





