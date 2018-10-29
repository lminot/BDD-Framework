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
    tags = {"@regression"})
public class TestRunner {

  @BeforeClass
  public static void setEnvironment() {
    String environment = System.getProperty("env", "stage");
    System.out.println("Running tests against: " + environment);
  }

  @AfterClass
  public static void tearDown() {
    DriverConfig.closeDriver();
  }
}
