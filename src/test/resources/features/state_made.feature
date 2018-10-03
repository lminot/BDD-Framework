Feature: Verify various aspects of the MedMen Statemade functionality

  Background: Click past 'Yes I'm 21'
    Given I navigate to the "Stage" homepage
    And I click "YES" on the age verification prompt
    And I click the enter button

  @statemade1
  Scenario: Statemade - verify landing page & navigation functionality
    When I select the statemade option from the top nav
    Then I am taken to the statemade landing page
    And the page has valid buttons
    And the page is displayed correctly

  @statemade
  Scenario: Statemade - verify effect page
    When I am on the statemade page
    And I select the effect button
    And I am taken to the effects page
    Then all the various effects are displayed

#  @statemade1
  Scenario: Statemade - verify products page
    When I am on the statemade page
    And I select the product type button
    And I am taken to the product type  page
    Then all the various product types are displayed

#  @statemade
  Scenario: Statemade - verify Max product page
    When I am on the statemade page
    And I select the effect button
    And I am taken to the effects page
    And I select the "Max" product
    Then the proper "Max" page is displayed
    And the bottom nav has Options all pens, drops, flower, and prerolls


  Scenario: Statemade - verify Joy product page
  Scenario: Statemade - verify Zen product page
  Scenario: Statemade - verify Ebb product page
  Scenario: Statemade - verify Zzz product page
  Scenario: Statemade - verify One product page
  Scenario: Statemade - verify Cbd product page
  Scenario: Statemade - verify pens product page
  Scenario: Statemade - verify drops product page
  Scenario: Statemade - verify flower product page
  Scenario: Statemade - verify prerolls product page
