package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.configs.EnvironmentConfig;
import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class EmailSignUpApiStepDefs {

    private RestClient restClient;
    public Response requestResponse;
    private Map<String, String> reqHeaders;
    private Map<String, String> queryParams;
    private String usersEmail;
    private String requestPayload;
    private String clutchBaseUrl;
    private String clutchBrand;
    private String clutchLocation;
    private String clutchTerminal;
    private String clutchCardSet;
    private String clutchApiKey;
    private String clutchApiSecret;
    private FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    private EnvironmentConfig environmentConfig = new EnvironmentConfig();


    @Given("^I have a valid email$")
    public void i_have_a_valid_email() {
        usersEmail = environmentConfig.getEmailAddress();
        setClutchVariables();
    }

    @When("^I execute a POST to the sign up endpoint$")
    public void i_execute_a_POST_to_the_sign_up_endpoint() {
        restClient = new RestClient();
        reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        queryParams = new HashMap<>();
        queryParams.put("email", usersEmail);
        String endpoint = "signup";

        requestResponse =
                restClient.executePostWithParams(environmentConfig.getMedmenApiBaseUrl() + endpoint, queryParams, reqHeaders, "");
        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @When("^I execute a POST to the sign up endpoint with a clutch event parameter$")
    public void i_execute_a_POST_to_the_sign_up_endpoint_with_a_clutch_event_parameter() {
        restClient = new RestClient();
        reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        queryParams = new HashMap<>();
        queryParams.put("email", usersEmail);
        queryParams.put("event", "statemadeEmailOptIn");
        String endpoint = "signup";

        requestResponse =
                restClient.executePostWithParams(environmentConfig.getMedmenApiBaseUrl() + endpoint, queryParams, reqHeaders, "");
        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @Then("^a valid response payload$")
    public void a_valid_response_payload() {
        String validResponse = "{\"status\":200,\"message\":\"ok\"}";
        Assert.assertEquals(validResponse, requestResponse.readEntity(String.class));
    }

    @Given("^I have a invalid email$")
    public void i_have_a_invalid_email() {
        usersEmail = "badString";
    }

    @Then("^an invalid response payload$")
    public void an_invalid_response_payload() {
        String invalidResponse = "{\"message\":\"Invalid Email Address.\"}";
        Assert.assertEquals(invalidResponse, requestResponse.readEntity(String.class));
    }

    @Then("^now my email is searchable in Clutch$")
    public void now_my_email_is_searchable_in_Clutch() {
        setClutchVariables();

        restClient = new RestClient();
        reqHeaders = new HashMap<>();

        String clutchSearchEndpoint = "/merchant/search";

        reqHeaders.put("brand", clutchBrand);
        reqHeaders.put("location", clutchLocation);
        reqHeaders.put("terminal", clutchTerminal);
        reqHeaders.put("cardSet", clutchCardSet);
        reqHeaders.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((clutchApiKey + ":" + clutchApiSecret).getBytes()));

        requestPayload = fileLoaderUtils.getPayloadWrapper("clutchEmailSearch.json");
        requestPayload = String.format(requestPayload, usersEmail);
        requestResponse = restClient.executePost(clutchBaseUrl + clutchSearchEndpoint, reqHeaders, requestPayload);
        Assert.assertEquals(200, requestResponse.getStatus());
    }

    private void setClutchVariables() {
        String configFile = environmentConfig.getConfigFile();
        clutchBaseUrl = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.base.url");
        clutchBrand = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.brand");
        clutchLocation = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.location");
        clutchTerminal = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.terminal");
        clutchCardSet = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.card.set");
        clutchApiKey = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.api.key");
        clutchApiSecret = fileLoaderUtils.getValueFromPropertyFile(configFile, "clutch.api.secret");
    }
}
