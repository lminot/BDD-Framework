package com.medmen.bdd.utils;

import java.io.IOException;
import java.io.InputStream;
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
    }
    return value.trim();
  }
}
