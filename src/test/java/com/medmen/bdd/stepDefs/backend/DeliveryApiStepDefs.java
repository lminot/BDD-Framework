package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class DeliveryApiStepDefs {

  public String requestPayload;
  public String baseUrl;
  public Response requestResponse;
  private FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
  private String environment = System.getProperty("env", "stage");

  @Given("^I have a valid getNearbyPickupStores payload$")
  public void i_have_a_valid_getNearbyPickupStores_payload() {

    CommonApiStepDefs.setBaseUrl(environment);
    CommonApiStepDefs.setUserEmail(environment);

    requestPayload = fileLoaderUtils.getPayloadWrapper("getNearbyPickupStores.json");

  }

  @When("^I execute a POST to the getNearbyPickupStores endpoint$")
  public void i_execute_a_POST_to_the_getNearbyPickupStores_endpoint() {
    RestClient restClient = new RestClient();

    Map<String, String> reqHeaders = new HashMap<>();
    reqHeaders.put("Content-Type", "application/json");
    reqHeaders.put("Accept", "application/json");
    baseUrl = CommonApiStepDefs.getBaseUrl();
    String endPoint = "stores/getNearbyPickupStores";

    requestResponse = restClient.executePost(baseUrl + endPoint, reqHeaders, requestPayload);
    CommonApiStepDefs.setStatusCode(requestResponse);
  }
}
