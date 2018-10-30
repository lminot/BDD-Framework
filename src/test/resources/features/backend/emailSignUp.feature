Feature: API testing
  As a user I should able to execute API calls for the Delivery API project

  @activeMonitor
  Scenario: Validate the Newsletter Email Signup API functionality with a  valid email
    Given I have a valid email
    When I execute a POST to the sign up endpoint
    Then I will expect a 200 response
    And a valid response payload

  @activeMonitor
  Scenario: Validate the Newsletter Email Signup API functionality with invalid email
    Given I have a invalid email
    When I execute a POST to the sign up endpoint
    Then I will expect a 200 response
    And an invalid response payload
