Feature: Medmen API testing
  As a user I should be able to execute API requests against the medmen API services

  @activeMonitor1
  Scenario: Validate create account API functionality
    Given I have valid credentials
    When I execute a POST to the register endpoint
    Then I will expect a 200 response
    And a valid response payload
    And the new account will be present in the database
