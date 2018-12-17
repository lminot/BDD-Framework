package com.medmen.bdd.stepDefs.backend;

import cucumber.api.java.en.Then;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CommonApiStepDefs {

    public static int responseCode;

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
}
