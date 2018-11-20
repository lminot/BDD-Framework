@regression @giftcards
Feature: Verify various aspects of the MedMen gift card functionality

  Background:
    Given reCaptcha is disabled

  Scenario: Check a gift card balance
    And I am on the gift cards page
    When I enter a valid card number
    And I enter a valid gift card pin
    And I select the check balance button
    Then the gift cards current balance will be displayed

  Scenario: Check multiple gift card balances
    And I am on the gift cards page
    And I enter a valid card number
    And I enter a valid gift card pin
    And reCaptcha is disabled
    And I select the check balance button
    And the gift cards current balance will be displayed
    When I select the Check another card button
    And I enter a new valid card number
    And I enter a new valid gift card pin
    And I select the check balance button
    Then the gift cards current balance will be displayed

  Scenario: Check gift card error handling, empty values
    And I am on the gift cards page
    And I enter nothing for card number
    And I enter nothing for gift card pin
    And I select the check balance button
    Then the error message "Please enter the gift card number." will be displayed
    And the error message "Please enter the gift card PIN." will be displayed

  Scenario: Check gift card error handling, bad card number
    And I am on the gift cards page
    When I enter a invalid card number
    And I enter a valid gift card pin
    And I select the check balance button
    Then the error message "The card number you entered is invalid, please try again." will be displayed

  Scenario: Check gift card error handling, bad pin number
    Given I am on the gift cards page
    When I enter a valid card number
    And I enter a invalid gift card pin
    And I select the check balance button
    Then the error message "Your card PIN is invalid, please try again." will be displayed
