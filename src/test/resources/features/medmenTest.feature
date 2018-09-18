@test
Feature: Create New Account in purchase flow
  As a new user
  I want to be able to create a new account while in the purchase flow on the medmen site

  Scenario: Create New Account in purchase flow
    Given I navigate to "http://medmen.com"
    And I click "YES" on  the age verification prompt
    And I click the enter button
    And I navigate to "https://menu.medmen.com/11/beverly-hills/"
    When I select "2" items to add to cart
    And I click checkout
#    Then I am taken to the Sign In page

#    And I enter "SuperSecretPassword!" into input field having id "password"
#    When I click on element having class "radius"
#    Then I wait 5 seconds for element having css "a[href='/logout']" to display