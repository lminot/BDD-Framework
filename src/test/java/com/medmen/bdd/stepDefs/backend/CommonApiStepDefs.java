package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CommonApiStepDefs {

  public Response requestResponse;
  public static int responseCode;

  public String getNearbyPickupStoresPayload;

  @Given("^I have a valid getNearbyPickupStores payload$")
  public void i_have_a_valid_getNearbyPickupStores_payload() {
    getNearbyPickupStoresPayload =
        "{\n"
            + "\t\"address\": \"10115 Jefferson Blvd, Culver City, CA 90232\",\n"
            + "\t\"lat\": \"34.0117964\",\n"
            + "\t\"lng\":\"-118.3905927\"\n"
            + "}";
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
    setStatusCode(requestResponse);
  }

  @Then("^I will expect a (\\d+) response$")
  public void i_will_expect_a_response(int expectedResponseCode) {
    assertEquals(expectedResponseCode, getStatusCode());
  }

  public static void setStatusCode(Response requestResponse){
    responseCode = requestResponse.getStatus();
  }

  public int getStatusCode(){
    return this.responseCode;
  }
}