Feature: Delivery API testing
  As a user I should able to execute API calls for the Delivery API project

  @test
  Scenario: Validate the get near by pickup stores endpoint
    Given I have a valid getNearbyPickupStores payload
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response
