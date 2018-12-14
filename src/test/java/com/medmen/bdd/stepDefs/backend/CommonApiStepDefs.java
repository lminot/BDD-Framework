package com.medmen.bdd.stepDefs.backend;

import cucumber.api.java.en.Then;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CommonApiStepDefs {

    public static int responseCode;
    public static String email;
    public static String baseUrl;


    @Then("^I will expect a (\\d+) response$")
    public void i_will_expect_a_response(int expectedResponseCode) {
        assertEquals(expectedResponseCode, getStatusCode());
    }

    public static void setStatusCode(Response requestResponse) {
        responseCode = requestResponse.getStatus();
    }

    public int getStatusCode() {
        return this.responseCode;
    }

    public static void setUserEmail(String expectedEmailString) {
        email = expectedEmailString;
    }

    public static String getUserEmail() {
        return email;
    }

    public static void setMedmenApiBaseUrl(String expectedBaseUrl) {
        baseUrl = expectedBaseUrl;
    }

    public static String getMedmenApiBaseUrl() {
        return baseUrl;
    }
}
