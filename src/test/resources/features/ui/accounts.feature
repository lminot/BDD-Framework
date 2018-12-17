Feature: Verify various aspects of the MedMen account functionality

  @activeMonitorUi
  Scenario: Create account - add new account via in-line credentials
    When I navigate to the create account page
    And I enter valid information in all required fields
    And I click the create account button
    Then I am directed to my store page
    And I have a logged in account

  Scenario: Create account - add new account via social auth Facebook
    When I navigate to the create account page
    And I select the Facebook sign in button
    And sign in via Facebook credentials
    Then I am directed to the complete profile page

  Scenario: Create account - add new account via social auth Google
    When I navigate to the create account page
    And I select the Google sign in button
    And sign in via Google credentials
    Then I am directed to the complete profile page

#  Scenario: Create New Account with items in cart
#    Given I navigate to "http://medmen.com"
#    And I click "YES" on  the age verification prompt
#    And I click the enter button
#    And I navigate to "https://menu.medmen.com/11/beverly-hills/"
#    When I select "2" items to add to cart
#    And I click checkout
#    Then I am taken to the Sign In page

#  Scenario: Create account - add new account with items in cart
#    When I select the Stores option from the top nav
#    And I select the "Los Angeles - Beverly Hills" store
#    And I select the shop now button
#    And I select "1" items to add to cart
#    And I select the cart from the top nav
#    And I select checkout
#    And I am taken to the sign in page
#    And I select create account
#    And I enter valid information in all required fields
#    And I click the create account button
#    And a new account is created
#    Then I am taken to the checkout page
#    And I click the place pickup order button
#    And I am taken to the order confirmed page
#
#  Scenario: Create account - add new account via social auth Facebook with items in cart
#    When I select the Stores option from the top nav
#    And I select the "Los Angeles - Beverly Hills" store
#    And I select the shop now button
#    And I select "1" items to add to cart
#    And I select the cart from the top nav
#    And I select checkout
#    And I select the Facebook sign in button
#    And sign in via Facebook credentials
#    Then a new account is created
#    And I am taken to the checkout page
#    And I click the place pickup order button
#    And I am taken to the order confirmed page
#
#  Scenario: Create account - add new account via social auth Google with items in cart
#    When I select the Stores option from the top nav
#    And I select the "Los Angeles - Beverly Hills" store
#    And I select the shop now button
#    And I select "1" items to add to cart
#    And I select the cart from the top nav
#    And I select checkout
#    And I select the Google sign in button
#    And sign in via Google credentials
#    Then a new account is created
#    And I am taken to the checkout page
#    And I click the place pickup order button
#    And I am taken to the order confirmed page

#  Scenario: Create account - trigger complete profile
#  Scenario: Order history - view account's order history
#  Scenario: Manage account - update with in-line fields
#  Scenario: Manage account - update / change account's password
#  Scenario: Manage account - update / change account's email
#
#  Scenario: Create account - trigger validation errors
#    When I navigate to the "Stage" create account page
#    And leave all required fields empty
#    And I click the Create Account button
#    Then I should see validation error text on all mandatory fields
#
#  Scenario: Existing account - sign in via email
#  Scenario: Existing account - sign in via social auth Facebook
#  Scenario: Existing account - sign in via social auth Google
#  Scenario: Existing account - forgot password
#  Scenario: Existing account - sign out




