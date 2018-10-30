package com.medmen.bdd.utils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

public class RestClient {

  private Client client = ClientBuilder.newClient();

  public Response executeGet(String url, Map<String, String> headers) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        for (Map.Entry<String, String> entry : headers.entrySet())
          webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
      }

      response = invocationBuilder.get();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public Response executePost(String url, Map<String, String> headers, String payload) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      // logging is working huzza!
      webTarget.register(
          new LoggingFeature(
              Logger.getLogger(getClass().getName()),
              Level.OFF,
              LoggingFeature.Verbosity.PAYLOAD_TEXT,
              8192));

      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        for (Map.Entry<String, String> entry : headers.entrySet())
          webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
      }

      response = invocationBuilder.post(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public Response executePostWithParams(
      String url, Map<String, String> queryParams, Map<String, String> headers, String payload) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      if (queryParams != null && !queryParams.isEmpty()) {
        for (Map.Entry<String, String> entry : queryParams.entrySet())
          webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
      }
      // logging is working huzza!
      webTarget.register(
          new LoggingFeature(
              Logger.getLogger(getClass().getName()),
              Level.OFF,
              LoggingFeature.Verbosity.PAYLOAD_TEXT,
              8192));

      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        for (Map.Entry<String, String> entry : headers.entrySet())
          webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
      }

      response = invocationBuilder.post(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public Response executePut(String url, Map<String, String> headers, String payload) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        for (Map.Entry<String, String> entry : headers.entrySet())
          webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
      }

      response = invocationBuilder.put(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }
}
