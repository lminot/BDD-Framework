@CPR
Feature: Verify medmen.com's newsletter email sign-up functionality

  @activeMonitorUi @isHeadless
  Scenario: Validate the Age-gate Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    When I click "YES" on the age verification prompt
    And I enter a valid email address into the "Age Gate Newsletter" sign up box
    Then I click the enter button
    And now my email is searchable in Clutch

  @activeMonitorUi
  Scenario: Validate the "exit-pop" Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button
    And I select the Who We Are top nav link
    When I trigger the exit pop overlay
    And I enter a valid email address into the "Get 10% Off Your First Purchase." sign up box
    And I click the exit-pop email submit button
    Then the exit-pop "Thank you for signing up." text is displayed
    And now my email is searchable in Clutch

  @activeMonitorUi
  Scenario: Validate the landing page Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button
    And I enter a valid email address into the "Keep in Touch" sign up box
    And I click the newsletter email submit button
    Then the "Thank you for signing up!" text is displayed
    And now my email is searchable in Clutch

  @activeMonitorUi
  Scenario: Validate the [statemade] Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button
    And I navigate to the statemade menu page
    When I select a product
    And I select the "Find Your Store" link
    And I enter a valid email address into the "Coming soon to:" sign up box
    And I click the statemade newsletter email submit button
    Then the statemade "Thank you for signing up." text is displayed
    And now my email is searchable in Clutch

#  @activeMonitorUi @isHeadless
  Scenario: Validate the menu site Newsletter Email Sign-up functionality with a valid email
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button
    And I navigate to the stores page
    And I select a store with a menu
    And I select the "Shop Now" button
    And I scroll down the page
    When I enter a valid email address into the menu "Keep in Touch" sign up box
    And I click the menu site newsletter email submit button
    Then the menu site "Thank you for signing up!" text is displayed
    And now my email is searchable in Clutch







