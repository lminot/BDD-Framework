package com.medmen.bdd.stepDefs.backend;

import com.jayway.jsonpath.JsonPath;
import com.medmen.bdd.utils.FileLoaderUtils;
import com.medmen.bdd.utils.RestClient;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.minidev.json.JSONArray;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static com.medmen.bdd.runner.TestRunner.environmentConfig;

public class DeliveryApiStepDefs {

    public String requestPayload;
    public String baseUrl;
    public Response requestResponse;
    private FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();

    @Before
    public void setUp() {
        CommonApiStepDefs.setUserEmail(fileLoaderUtils.getValueFromPropertyFile(environmentConfig.getConfigFile(), "email"));
        CommonApiStepDefs.setMedmenApiBaseUrl(fileLoaderUtils.getValueFromPropertyFile(environmentConfig.getConfigFile(), "medmen.api.base.url"));
    }

    @Given("^I have a valid getNearbyPickupStores payload$")
    public void i_have_a_valid_getNearbyPickupStores_payload() {
        String latitude = "34.0117964";
        String longitude = "-118.3905927";

        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearbyPickupStores.json");
        setRequestPayload(String.format(requestPayload, latitude, longitude));
    }

    @Given("^I have a valid getNearbyPickupStores payload outside delivery range$")
    public void i_have_a_valid_getNearbyPickupStores_payload_outside_delivery_range() {
        String latitude = "34.0117964";
        String longitude = "118.3905927";

        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearbyPickupStores.json");
        setRequestPayload(String.format(requestPayload, latitude, longitude));
    }

    @Given("^I have a getNearbyPickupStores payload missing \"([^\"]*)\"$")
    public void i_have_a_getNearbyPickupStores_payload_missing(String badPayload) {
        String latitude = "";
        String longitude = "";
        if (badPayload.contains("latitude")) {
            longitude = "-118.3905927";
        } else if (badPayload.contains("longitude")) {
            latitude = "34.0117964";
        }

        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearbyPickupStores.json");
        setRequestPayload(String.format(requestPayload, latitude, longitude));
    }

    @Then("^a proper false response payload$")
    public void a_proper_false_response_payload() {
        String success =
                JsonPath.parse(requestResponse.readEntity(String.class)).read("$.success").toString();
        Assert.assertFalse(Boolean.valueOf(success));
    }

    @When("^I execute a POST to the getNearbyPickupStores endpoint$")
    public void i_execute_a_POST_to_the_getNearbyPickupStores_endpoint() {
        RestClient restClient = new RestClient();
        Map<String, String> reqHeaders = new HashMap<>();

        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Accept", "application/json");
        baseUrl = CommonApiStepDefs.getMedmenApiBaseUrl();
        String endPoint = "stores/getNearbyPickupStores";

        requestResponse = restClient.executePost(baseUrl + endPoint, reqHeaders, getRequestPayload());
        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @Given("^I have a valid getNearestDeliveryStore payload$")
    public void i_have_a_valid_getNearestDeliveryStore_payload() {
        String address = "10115 Jefferson Blvd, Culver City, CA 90232";
        String latitude = "34.075713";
        String longitude = "-118.389136";
        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearestDeliveryStore.json");
        setRequestPayload(String.format(requestPayload, address, latitude, longitude));
    }

    @Then("^all returned stores have a distance less than (\\d+) miles$")
    public void all_returned_stores_have_a_distance_less_than_miles(int storeDistance) {
        JSONArray storesArrayOver25miles =
                JsonPath.parse(requestResponse.readEntity(String.class))
                        .read("$.stores[?(@.distance > " + storeDistance + ")]");
        Assert.assertEquals(storesArrayOver25miles.size(), 0);
    }

    @Given("^I have a valid getNearbyPickupStores payload nearest to \"([^\"]*)\"$")
    public void i_have_a_valid_getNearbyPickupStores_payload_nearest_to(String arg1) {
        String latitude = "34.0117964";
        String longitude = "-118.3905927";

        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearbyPickupStores.json");
        setRequestPayload(String.format(requestPayload, latitude, longitude));
    }

    @Then("^the results will list \"([^\"]*)\" first$")
    public void the_results_will_list_first(String expectedStoreName) {
        String storeName =
                JsonPath.parse(requestResponse.readEntity(String.class))
                        .read("$.stores[0].name")
                        .toString();
        Assert.assertEquals(expectedStoreName, storeName);
    }

    @Given("^I have a valid getNearestDeliveryStore payload outside delivery range$")
    public void i_have_a_valid_getNearestDeliveryStore_payload_outside_delivery_range() {
        String address = "10115 Jefferson Blvd, Culver City, CA 90232";
        String latitude = "-34.075713";
        String longitude = "-118.389136";

        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearestDeliveryStore.json");
        setRequestPayload(String.format(requestPayload, address, latitude, longitude));
    }

    @When("^I execute a POST to the getNearestDeliveryStore endpoint$")
    public void i_execute_a_POST_to_the_getNearestDeliveryStore_endpoint() {
        RestClient restClient = new RestClient();

        Map<String, String> reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Accept", "application/json");
        baseUrl = CommonApiStepDefs.getMedmenApiBaseUrl();
        String endPoint = "stores/getNearestDeliveryStore";

        requestResponse = restClient.executePost(baseUrl + endPoint, reqHeaders, getRequestPayload());
        CommonApiStepDefs.setStatusCode(requestResponse);
    }

    @Given("^I have a valid getNearestDeliveryStore payload for \"([^\"]*)\"$")
    public void i_have_a_valid_getNearestDeliveryStore_payload_for(String expectedAddress) {

        String address = "10115 Jefferson Blvd, Culver City, CA 90232";
        String latitude = "34.075713";
        String longitude = "-118.389136";
        requestPayload = fileLoaderUtils.getPayloadWrapper("getNearestDeliveryStore.json");
        setRequestPayload(String.format(requestPayload, address, latitude, longitude));
    }

    @Then("^the result will list \"([^\"]*)\"$")
    public void the_result_will_list(String expectedStoreName) {
        String storeName =
                JsonPath.parse(requestResponse.readEntity(String.class)).read("$.store.name").toString();
        Assert.assertEquals(expectedStoreName, storeName);
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }
}
