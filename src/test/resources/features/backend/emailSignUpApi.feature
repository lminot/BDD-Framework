@activeMonitorApi
Feature: Medmen API testing
  As a user I should be able to execute API requests against the medmen services

  Scenario: Validate the Newsletter Email Signup API functionality with a valid email
    Given I have a valid email
    When I execute a POST to the sign up endpoint
    Then I will expect a 200 response
    And a valid response payload

  Scenario: Validate the Newsletter Email Signup API functionality with a valid email & clutch event
    Given I have a valid email
    When I execute a POST to the sign up endpoint with a clutch event parameter
    Then I will expect a 200 response
    And a valid response payload

  Scenario: Validate the Newsletter Email Signup API functionality with invalid email
    Given I have a invalid email
    When I execute a POST to the sign up endpoint
    Then I will expect a 200 response
    And an invalid response payload
