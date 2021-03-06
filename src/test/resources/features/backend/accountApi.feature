@activeMonitorApi @CPR
Feature: Medmen Account API testing
  As a user I should be able to execute API requests against the medmen API services

  @stageOnlyTest
  Scenario: Validate create account API functionality
    Given I have valid credentials
    When I execute a POST to the register endpoint
    Then I will expect a 200 response
    And a valid response payload for create account
#    And the new account will be present in the database

  @stageOnlyTest
  Scenario: Validate account login API functionality
    Given I have a valid account
    When I execute a POST to the login endpoint
    Then I will expect a 200 response
    And a valid response payload for login

  @stageOnlyTest
  Scenario: Validate reset password functionality
    Given I have a valid account
    When I execute a POST to the forgot password endpoint
    Then I will expect a 200 response
    And a valid response payload for reset password

#  Scenario: Validate create account via Facebook functionality
#    Given I have a valid Facebook account
#    When I execute a POST to the social register endpoint
#    Then I will expect a 200 response
#    And a valid response payload for login
#    #And the new account will be present in the database
#
#  Scenario: Validate create account via Google functionality
#    Given I have a valid Google account
#    When I execute a POST to the social register endpoint
#    Then I will expect a 200 response
#    And a valid response payload for login
#    #And the new account will be present in the database
#
#  Scenario: Validate social login via Facebook functionality
#    Given I have a valid Facebook account
#    When I execute a POST to the social login endpoint
#    Then I will expect a 200 response
#    And a valid response payload for login
#
#  Scenario: Validate social login via Google functionality
#    Given I have a valid Google account
#    When I execute a POST to the social login endpoint
#    Then I will expect a 200 response
#    And a valid response payload for login
#
#    # todo add error cases