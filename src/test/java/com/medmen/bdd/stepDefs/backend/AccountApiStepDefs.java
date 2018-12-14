package com.medmen.bdd.stepDefs.backend;

import com.jayway.jsonpath.JsonPath;
import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.JdbcUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountApiStepDefs {
    private RestClient restClient;
    private FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    public Response requestResponse;
    private Map<String, String> reqHeaders;
    private Map<String, String> queryParams;
    private String environment = System.getProperty("env", "stage");
    private String email;
    private String baseUrl;
    private String requestPayload;
    private String emailWithTimeStamp;

    @Given("^I have valid credentials$")
    public void i_have_valid_credentials() {
//todo fix config injection
//    environment = System.getProperty("env", "stage");
//    CommonApiStepDefs.setBaseUrl(environment);
        CommonApiStepDefs.setUserEmail(environment);

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
        emailWithTimeStamp = String.format("medmentest420+%d@gmail.com", System.currentTimeMillis());
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
                        emailWithTimeStamp,
                        password);
    }

    @When("^I execute a POST to the register endpoint$")
    public void i_execute_a_POST_to_the_register_endpoint() {
        restClient = new RestClient();
        reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Accept", "application/json");
        baseUrl = "http://medmen-api-staging.havenagencyapps.com/api/";
        requestResponse = restClient.executePost(baseUrl + "register", reqHeaders, requestPayload);

        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @Then("^a valid response payload for create account$")
    public void a_valid_response_payload_for_create_account() {
        String successTrue =
                JsonPath.parse(requestResponse.readEntity(String.class)).read("$.success").toString();

        Assert.assertTrue(Boolean.valueOf(successTrue));
    }

    @Then("^the new account will be present in the database$")
    public void the_new_account_will_be_present_in_the_database() throws SQLException {
        JdbcUtils jdbcUtils = new JdbcUtils();
        ResultSet resultSet =
                jdbcUtils.executeSelectQuery(
                        "SELECT * FROM users WHERE email = '" + emailWithTimeStamp + "'");

        while (resultSet.next()) {
            System.out.println("********");
            System.out.println(resultSet.getString("created_at"));
            System.out.println("********");
        }
        // todo if the account is "new" delete it from DB for clean up after test runs
    }

    @Given("^I have a valid account$")
    public void i_have_a_valid_account() {
        //todo fix config injection
//    CommonApiStepDefs.setBaseUrl(environment);
        CommonApiStepDefs.setUserEmail(environment);

        email = CommonApiStepDefs.getUserEmail();

        requestPayload = fileLoaderUtils.getPayloadWrapper("signIn.json");
        String password = "foo99bar";
        requestPayload = String.format(requestPayload, email, password);
    }

    @When("^I execute a POST to the login endpoint$")
    public void i_execute_a_POST_to_the_login_endpoint() {
        restClient = new RestClient();
        reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Accept", "application/json");
        baseUrl = "http://medmen-api-staging.havenagencyapps.com/api/";
        requestResponse = restClient.executePost(baseUrl + "login", reqHeaders, requestPayload);

        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @Then("^a valid response payload for login$")
    public void a_valid_response_payload_for_login() {

        String successTrue =
                JsonPath.parse(requestResponse.readEntity(String.class)).read("$.success").toString();

        Assert.assertTrue(Boolean.valueOf(successTrue));
    }

    @When("^I execute a POST to the forgot password endpoint$")
    public void i_execute_a_POST_to_the_forgot_password_endpoint() {
        restClient = new RestClient();
        reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Accept", "application/json");
        email = CommonApiStepDefs.getUserEmail();
        baseUrl = "http://medmen-api-staging.havenagencyapps.com/api/";

        requestPayload = fileLoaderUtils.getPayloadWrapper("forgetPassword.json");
        requestPayload = String.format(requestPayload, email);
        requestResponse = restClient.executePost(baseUrl + "recover", reqHeaders, requestPayload);

        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @Then("^a valid response payload for reset password$")
    public void a_valid_response_payload_for_reset_password() {
        String emailMessage =
                JsonPath.parse(requestResponse.readEntity(String.class)).read("$.data.message").toString();
        Assert.assertEquals(emailMessage, "A reset email has been sent! Please check your email.");
    }
}
