#@apiTest
Feature: Delivery API testing
  As a user I should be able to execute API calls for the Delivery API project

  Scenario: Validate the getNearbyPickupStores API
    Given I have a valid getNearbyPickupStores payload
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response

  Scenario: Validate getNearbyPickupStores endpoint only returns stores within a 25 mile radius
    Given I have a valid getNearbyPickupStores payload
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response
    And all returned stores have a distance less than 25 miles

  Scenario: Validate the getNearbyPickupStores endpoint for specific location
    Given I have a valid getNearbyPickupStores payload nearest to "MedMen LAX"
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response
    And the results will list "MedMen LAX" first

  Scenario: Validate the getNearbyPickupStores endpoint for specific location outside delivery range
    Given I have a valid getNearbyPickupStores payload outside delivery range
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 200 response
    And a proper false response payload

  Scenario: Validate the getNearbyPickupStores API negative cases
    Given I have a getNearbyPickupStores payload missing "latitude"
    And I execute a POST to the getNearbyPickupStores endpoint
    And I will expect a 400 response
    And a proper false response payload
    And I have a getNearbyPickupStores payload missing "longitude"
    When I execute a POST to the getNearbyPickupStores endpoint
    Then I will expect a 400 response
    And a proper false response payload
    And I have a getNearbyPickupStores payload missing "all fields"
    And I execute a POST to the getNearbyPickupStores endpoint
    And I will expect a 400 response
    And a proper false response payload

  Scenario: Validate the getNearestDeliveryStore API
    Given I have a valid getNearestDeliveryStore payload
    When I execute a POST to the getNearestDeliveryStore endpoint
    Then I will expect a 200 response

  Scenario: Validate the getNearestDeliveryStore endpoint for specific location
    Given I have a valid getNearestDeliveryStore payload for "MedMen Beverly Hills"
    When I execute a POST to the getNearestDeliveryStore endpoint
    Then I will expect a 200 response
    And the result will list "MedMen Beverly Hills"

#TODO add getNearestDeliveryStore error cases

  @apiTest
  Scenario: Validate the getNearestDeliveryStore API
    Given I have a valid getNearestDeliveryStore payload outside delivery range
    When I execute a POST to the getNearestDeliveryStore endpoint
    Then I will expect a 200 response
    And a proper false response payload
