package com.medmen.bdd.runner;

import com.medmen.bdd.configs.DriverConfig;
import org.junit.AfterClass;
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
    tags = {"@test"})

public class TestRunner {

  @AfterClass
  public static void tearDown() {
    DriverConfig.closeDriver();
  }
}
