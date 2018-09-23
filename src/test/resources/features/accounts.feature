@test
Feature: Verify various aspects of the MedMen account functionality

  Background: Yes I'm 21
    Given I navigate to the "Stage" homepage
    And I click "YES" on the age verification prompt
    And I click the enter button

  Scenario: Create New Account with in line credentials
    Given I navigate to the "Stage" create account page
    And I enter valid information in all required fields
#    When I click the Create Account button
#    Then I am directed to my Store Page
#    And I am I have a logged in account

#  Scenario: Create New Account with items in cart
#    Given I navigate to "http://medmen.com"
#    And I click "YES" on  the age verification prompt
#    And I click the enter button
#    And I navigate to "https://menu.medmen.com/11/beverly-hills/"
#    When I select "2" items to add to cart
#    And I click checkout
#    Then I am taken to the Sign In page

