package com.medmen.bdd.utils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.Map;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

public class RestClient {

  private Client client = ClientBuilder.newClient();

  public Response executeGet(String url, Map headers) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        Iterator it = headers.entrySet().iterator();
        while (it.hasNext()) {
          Map.Entry pairs = (Map.Entry) it.next();
          invocationBuilder.header(pairs.getKey().toString(), pairs.getValue());
        }
      }

      response = invocationBuilder.get();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public Response executePost(String url, Map headers, String payload) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        Iterator it = headers.entrySet().iterator();
        while (it.hasNext()) {
          Map.Entry pairs = (Map.Entry) it.next();
          invocationBuilder.header(pairs.getKey().toString(), pairs.getValue());
        }
      }

      response = invocationBuilder.post(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public Response executePut(String url, Map headers, String payload) {
    Response response = null;
    try {

      WebTarget webTarget = client.target(url);
      Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

      if (headers != null && !headers.isEmpty()) {
        Iterator it = headers.entrySet().iterator();
        while (it.hasNext()) {
          Map.Entry pairs = (Map.Entry) it.next();
          invocationBuilder.header(pairs.getKey().toString(), pairs.getValue());
        }
      }

      response = invocationBuilder.put(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }
}
