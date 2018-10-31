package com.medmen.bdd.utils;

import java.io.*;
import java.util.Properties;

public class FileLoaderUtils {

  private static InputStream input = null;
  private static Properties prop = new Properties();

  public static String getValueFromPropertyFile(String propertyFilePath, String key) {

    String value = null;
    input = FileLoaderUtils.class.getClassLoader().getResourceAsStream(propertyFilePath);
    try {
      prop.load(input);
      value = prop.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return value.trim();
    }
  }

  public static String getPayloadWrapper(String propertyFilePath) {
    StringBuilder resultStringBuilder = new StringBuilder();
    String payloadLocation = "requestPayloads/";
    input = FileLoaderUtils.class.getClassLoader().getResourceAsStream(payloadLocation + propertyFilePath);

    try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return resultStringBuilder.toString();
    }
  }
}
