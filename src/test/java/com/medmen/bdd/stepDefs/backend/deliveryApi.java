package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class deliveryApi {

  public Response requestResponse;
  public int responseCode;

  public String getNearbyPickupStoresPayload;

  @Given("^I have a valid getNearbyPickupStores payload$")
  public void i_have_a_valid_getNearbyPickupStores_payload() {
    getNearbyPickupStoresPayload =
        "{\n" + "  \"lat\": \"34.195804\",\n" + "  \"lng\": \"-119.174093\"\n" + "}";
  }

  @When("^I execute a POST to the getNearbyPickupStores endpoint$")
  public void i_execute_a_POST_to_the_getNearbyPickupStores_endpoint() {
    RestClient restClient = new RestClient();

    Map<String, String> reqHeaders = new HashMap<>();
    reqHeaders.put("Content-Type", "application/json");
    reqHeaders.put("Accept", "application/json");
    String baseUrl =
        "http://medmen-api-staging.havenagencyapps.com/api/stores/getNearbyPickupStores";

    requestResponse = restClient.executePost(baseUrl, reqHeaders, getNearbyPickupStoresPayload);
    responseCode = requestResponse.getStatus();
  }

  @Then("^I will expect a (\\d+) response$")
  public void i_will_expect_a_response(int expectedResponseCode) {
    assertEquals(expectedResponseCode, responseCode);
  }
}
