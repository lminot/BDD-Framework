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

    public static EnvironmentConfig environmentConfig = new EnvironmentConfig();

    @BeforeClass
    public static void printRunSettings() {
        System.out.println("Test Environment: " + environmentConfig.getEnvironment());
        if (System.getProperty("browser") != null) {
            System.out.println("Browser: " + environmentConfig.getBrowser());
            System.out.println("Browser Location: " + environmentConfig.getBrowserLocation());
            System.out.println("Headless Browser: " + environmentConfig.getHeadless());
        }
        System.out.println("\n");
    }

    @AfterClass
    public static void tearDown() {
        DriverConfig.closeDriver();
    }
}
