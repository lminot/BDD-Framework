package com.medmen.bdd.stepDefs.backend;

import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.java.en.Then;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CommonApiStepDefs {

  private static FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
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

  public static String getUserEmail() {
    return email;
  }

  public static String getBaseUrl() {
    return baseUrl;
  }

  public static void setUserEmail(String environment) {
    if (environment.toLowerCase().contains("localhost")) {
      email = fileLoaderUtils.getValueFromPropertyFile("local.properties", "email");
    } else if (environment.toLowerCase().contains("stage")) {
      email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email");
    } else if (environment.toLowerCase().contains("prod")) {
      email = fileLoaderUtils.getValueFromPropertyFile("prod.properties", "email");
    }
  }

  public static void setBaseUrl(String environment) {
    if (environment.toLowerCase().contains("localhost")) {
      baseUrl = "http://localhost/api/";
    } else if (environment.toLowerCase().contains("stage")) {
      baseUrl = "http://medmen-api-staging.havenagencyapps.com/api/";
    } else if (environment.toLowerCase().contains("prod")) {
      baseUrl = "http://menu-api.medmen.com/api";
    }
  }
}
