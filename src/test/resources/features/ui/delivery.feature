@regression @delivery
Feature: Verify various aspects of the MedMen delivery functionality

  Scenario: Verify the delivery eligibility of an address that meets delivery restrictions for the Beverly Hills store
    Given I am on the store landing page
    And I select Delivery
    When I enter an address that meets delivery restrictions for the Beverly Hills store "1715 Camden Ave, Los Angeles, CA 90025, USA"
    Then I am shown the proper Delivery Available From prompt

  Scenario: Verify the delivery eligibility of an address that does NOT meet delivery restrictions for the Beverly Hills store
    Given I am on the store landing page
    And I select Delivery
    When I enter an address that does NOT meet delivery restrictions for the Beverly Hills store "618 N. Rodeo Dr, Beverly Hills, CA 90210, USA"
    Then I am shown the proper Delivery Available From prompt

  Scenario: Verify the delivery eligibility of an address that meets delivery restrictions for the Beverly Hills store, but outside delivery radius of 5 miles
    Given I am on the store landing page
    And I select Delivery
    When I enter an address that meets restrictions, but is outside 5 miles "428 14th St, Santa Monica, CA 90402, USA"
    Then I am shown the "Sorry, delivery is not available to this address at this time. Please try another address or ordering for pickup instead." prompt
    And the Nearest Pickup Location is displayed

  Scenario: Verify the delivery eligibility of an restricted address within a 5 mile radius of the Beverly Hills store.
    Given I am on the store landing page
    And I select Delivery
    When I enter an address that meets restrictions, but is outside 5 miles "11000 Wilshire Boulevard, Los Angeles, CA 90024, USA"
    Then I am shown the "Sorry, delivery is not available to this address at this time. Please try another address or ordering for pickup instead." prompt

#  Scenario: Check address that is in valid bounds of Beverly Hills and DTLA, but closer to LAX.
#  740 S Hobart Boulevard, Los Angeles, CA 90005
#
#  Scenario: Check address that is in valid bounds of Beverly Hills and DTLA, but LAX has stopped accepting deliveries.
#  740 S Hobart Boulevard, Los Angeles, CA 90005 (disable DTLA)
