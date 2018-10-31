package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class CreateAccountApiStepDefs {
  private RestClient restClient;
  public Response requestResponse;
  private Map<String, String> reqHeaders;
  private Map<String, String> queryParams;
  private String environment;
  private String email;
  private String baseUrl;
  private String requestPayload;

  @Given("^I have valid credentials$")
  public void i_have_valid_credentials() {

    FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    environment = System.getProperty("env", "stage");
    if (environment.toLowerCase().contains("localhost")) {
      baseUrl = "http://localhost/api/";
      email = fileLoaderUtils.getValueFromPropertyFile("local.properties", "email");
    } else if (environment.toLowerCase().contains("stage")) {
      baseUrl = "http://medmen-api-staging.havenagencyapps.com/api/";
      email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email");
    } else if (environment.toLowerCase().contains("prod")) {
      baseUrl = "http://menu-api.medmen.com/api";
      email = fileLoaderUtils.getValueFromPropertyFile("prod.properties", "email");
    }

    requestPayload = fileLoaderUtils.getPayloadWrapper("createAccount.json");

    String phoneNumber = "8008749200";
    String dobMonth = "09";
    String dobDay = "15";
    String dobYear = "1978";
    String zipCode = "91103";
    String state = "CA";
    String country = "US";
    int preferredStore = 26;
    String city = "Culver City";
    String firstName = "automationFirstName";
    String lastName = "automationLastName";
    long emailTimeStamp = System.currentTimeMillis();
    String password = "popeye123";

    requestPayload =
        String.format(
            requestPayload,
            phoneNumber,
            dobMonth,
            dobDay,
            dobYear,
            zipCode,
            state,
            country,
            preferredStore,
            city,
            firstName,
            lastName,
            emailTimeStamp,
            password);
  }

  @When("^I execute a POST to the register endpoint$")
  public void i_execute_a_POST_to_the_register_endpoint() {
    restClient = new RestClient();
    reqHeaders = new HashMap<>();
    reqHeaders.put("Content-Type", "application/json");
    reqHeaders.put("Accept", "application/json");
    requestResponse = restClient.executePost(baseUrl + "register", reqHeaders, requestPayload);

    CommonApiStepDefs.setStatusCode(requestResponse);
  }

  @Then("^a valid response payload for create account$")
  public void a_valid_response_payload_for_create_account() {
    String validResponse =
        "{\"success\":false,\"errors\":{\"validCaptcha\":[\"Please prove you are not a robot.\"]}}";
    Assert.assertEquals(validResponse, requestResponse.readEntity(String.class));
  }

//  @Then("^the new account will be present in the database$")
//  public void the_new_account_will_be_present_in_the_database() {
//    // Write code here that turns the phrase above into concrete actions
//    // todo implement this after captcha issue is fixed
//    throw new PendingException();
//  }
}
