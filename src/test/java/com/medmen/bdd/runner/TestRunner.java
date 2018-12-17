package com.medmen.bdd.runner;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.configs.EnvironmentConfig;
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
        tags = {"@activeMonitorApi"})
public class TestRunner {

    private static EnvironmentConfig ec;

    @BeforeClass
    public static void printRunSettings() {
        ec = new EnvironmentConfig();
        System.out.println("Test Environment: " + ec.getEnvironment());
        System.out.println("Browser: " + ec.getBrowser());
        System.out.println("Browser Location: " + ec.getBrowserLocation());
        System.out.println("Headless Browser: " + ec.getHeadless());
        System.out.println("\n");
    }

    @AfterClass
    public static void tearDown() {
        DriverConfig.closeDriver();
    }
}
