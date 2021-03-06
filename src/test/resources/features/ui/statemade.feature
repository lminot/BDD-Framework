@regression @statemade
Feature: Verify various aspects of the MedMen Statemade functionality

  Background: Click past 'Yes I'm 21'
    Given I navigate to the Medmen homepage
    And I click "YES" on the age verification prompt
    And I click the enter button

  Scenario: Statemade - verify landing experience & navigation functionality
    When I select the statemade option from the top nav
    Then I am taken to the statemade landing page
    And the page has valid buttons
    And the page is displayed correctly

  Scenario: Statemade - verify effect page
    When I am on the statemade page
    And I select the effect button
    And I am taken to the effects page
    Then all the various effects are displayed

  Scenario: Statemade - verify products page
    When I am on the statemade page
    And I select the product type button
    And I am taken to the product type page
    Then all the various product types are displayed

  Scenario: Statemade - verify products page bottom nav options
    When I am on the statemade menu page
    Then the bottom nav is fully functional

  Scenario: Statemade - verify all products & effect descriptions
    When I am on the statemade menu page
    And I navigate through the various effects
    Then the all products effect description is properly displayed

  Scenario: Statemade - verify pen products & effect descriptions
    When I am on the statemade menu page
    Then I select the "pens" product
    And I select each effect and verify their descriptions

#  Scenario: Statemade - verify drops products & effect descriptions
#    When I am on the statemade menu page
#    And I select the "drops" product
#    And I select each effect
#    Then each "drops" effect description is properly displayed
#
#  Scenario: Statemade - verify flower products & effect descriptions
#    When I am on the statemade menu page
#    And I select the "flower" product
#    And I select each effect
#    Then each "flower" effect description is properly displayed
#
#  Scenario: Statemade - verify pre-rolls products & effect descriptions
#    When I am on the statemade menu page
#    And I select the "pre-rolls" product
#    And I select each effect
#    Then each "pre-rolls" effect description is properly displayed


