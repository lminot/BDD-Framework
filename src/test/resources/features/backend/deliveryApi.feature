Feature: Delivery API testing
  As a user I should able to execute API calls for the Delivery API project

  @test
  Scenario: Validate the get near by pickup stores endpoint
    Given I have a valid getNearbyPickupStores payload
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response

  Scenario: Validate the get near by pickup stores endpoint only returns stores within a 25 mile radius
    Given I have a valid getNearbyPickupStores payload
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response
    And all returned stores have a distance less than 25 miles

  Scenario: Validate the get near by pickup stores endpoint for specific location
    Given I have a valid getNearbyPickupStores payload for "Beverly Hills"
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response
    And the results will list "Beverly Hills" first

