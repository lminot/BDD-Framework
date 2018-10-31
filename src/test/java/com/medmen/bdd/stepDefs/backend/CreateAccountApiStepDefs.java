package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;

import javax.ws.rs.core.Response;
import java.util.Map;

public class CreateAccountApiStepDefs {
  private FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();

  private RestClient restClient;
  public Response requestResponse;
  private Map<String, String> reqHeaders;
  private Map<String, String> queryParams;
  private String environment;
  private String email;
  private String baseUrl;

  @Given("^I have valid credentials$")
  public void i_have_valid_credentials() {

    String payload = fileLoaderUtils.getPayloadWrapper("createAccount.json");

    System.out.println("****** payload=" + payload);
  }

  //    @When("^I execute a POST to the register endpoint$")
  //    public void i_execute_a_POST_to_the_register_endpoint() throws Throwable {
  //        // Write code here that turns the phrase above into concrete actions
  //        throw new PendingException();
  //    }
  //
  //    @Then("^the new account will be present in the database$")
  //    public void the_new_account_will_be_present_in_the_database() throws Throwable {
  //        // Write code here that turns the phrase above into concrete actions
  //        throw new PendingException();
  //    }

}
