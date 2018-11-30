package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class EmailSignUpApiStepDefs {

  private RestClient restClient;
  public Response requestResponse;
  private Map<String, String> reqHeaders;
  private Map<String, String> queryParams;
  private String environment;
  private String email;
  private String baseUrl;

  @Given("^I have a valid email$")
  public void i_have_a_valid_email() {
    FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    environment = System.getProperty("env", "stage");
    if (environment.toLowerCase().contains("localhost")) {
      baseUrl = "https://medmen.com/";
      email = fileLoaderUtils.getValueFromPropertyFile("local.properties", "email");
    } else if (environment.toLowerCase().contains("stage")) {
      baseUrl = "https://medmen.com/";
      email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email");
    } else if (environment.toLowerCase().contains("prod")) {
      baseUrl = "https://medmen.com/";
      email = fileLoaderUtils.getValueFromPropertyFile("prod.properties", "email");
    }
  }

  @When("^I execute a POST to the sign up endpoint$")
  public void i_execute_a_POST_to_the_sign_up_endpoint() {
    restClient = new RestClient();
    reqHeaders = new HashMap<>();
    reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
    queryParams = new HashMap<>();
    queryParams.put("email", email);
    String endpoint = "signup";
    //baseUrl = "https://medmen.com/";

    requestResponse =
        restClient.executePostWithParams(baseUrl + endpoint, queryParams, reqHeaders, "");
    CommonApiStepDefs.setStatusCode(requestResponse);
  }

  @When("^I execute a POST to the sign up endpoint with a clutch event parameter$")
  public void i_execute_a_POST_to_the_sign_up_endpoint_with_a_clutch_event_parameter() {
    restClient = new RestClient();
    reqHeaders = new HashMap<>();
    reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
    queryParams = new HashMap<>();
    queryParams.put("email", email);
    queryParams.put("event", "statemadeEmailOptIn");
    String endpoint = "signup";
    //baseUrl = "https://medmen.com/";

    requestResponse =
        restClient.executePostWithParams(baseUrl + endpoint, queryParams, reqHeaders, "");
    CommonApiStepDefs.setStatusCode(requestResponse);
  }

  @Then("^a valid response payload$")
  public void a_valid_response_payload() {
    String validResponse = "{\"status\":200,\"message\":\"ok\"}";
    Assert.assertEquals(validResponse, requestResponse.readEntity(String.class));
  }

  @Given("^I have a invalid email$")
  public void i_have_a_invalid_email() {
    email = "badString";
  }

  @Then("^an invalid response payload$")
  public void an_invalid_response_payload() {
    String invalidResponse = "{\"status\":400,\"message\":\"Invalid Email Address.\"}";
    Assert.assertEquals(invalidResponse, requestResponse.readEntity(String.class));
  }
}
