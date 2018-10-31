@activeMonitorApi
Feature: Medmen Account API testing
  As a user I should be able to execute API requests against the medmen API services

  Scenario: Validate create account API functionality
    Given I have valid credentials
    When I execute a POST to the register endpoint
    Then I will expect a 200 response
    And a valid response payload for create account
#    And the new account will be present in the database
# todo add error casses

  @activeMonitorApi1
  Scenario: Validate account login API functionality
    Given I have a valid account
    When I execute a POST to the login endpoint
    Then I will expect a 200 response
    And a valid response payload for login