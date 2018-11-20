package com.medmen.bdd.runner;

import com.medmen.bdd.configs.DriverConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = {"classpath:com.medmen.bdd.stepDefs"},
    format = {
      "pretty",
      "html:target/cucumber-reports/cucumber-pretty",
      "json:target/cucumber-reports/CucumberTestReport.json",
      "rerun:target/cucumber-reports/rerun.txt"
    },
    tags = {"@statemade"})
public class TestRunner {

  public static String getEnvironment() {
    return System.getProperty("env", "stage");
  }

  public static String getBrowser() {
    return System.getProperty("browser", "firefox");
  }

  public static String getHeadless() {
    return System.getProperty("headless", "true");
  }

  public static String getBrowserLocation() {
    return System.getProperty("browserLocation", "local");
  }

  @BeforeClass
  public static void printRunSettings() {
    System.out.println("Test Environment: " + getEnvironment());
    if (System.getProperty("browser") != null) {
      System.out.println("Browser: " + getBrowser());
      System.out.println("Browser Location: " + getBrowserLocation());
      System.out.println("Headless Browser: " + getHeadless());
    }
    System.out.println("\n");
  }

  @AfterClass
  public static void tearDown() {
    DriverConfig.closeDriver();
  }


    public String fizzString(String str) {

      String answer = "";

      if( str.substring(0,1).equals("f") && str.substring(str.length() - 1).equals("b")){
        answer = "FizzBuzz";
      }

      else if(str.substring(str.length() - 1).equals("b")){
        answer = "Buzz";
      }

      else if(str.substring(0,1).equals("f")) {
        answer =  "Fizz";
      }

      return answer;
    }


}
