# Medmen BDD Automation

Selenium-cucumber: Automation Testing Using Java

Selenium-cucumber is a behavior driven development (BDD) approach to write automation test script to test Web.
It enables you to write and execute automated acceptance/regression tests.

Local setup
--------------
Download & install [Java](https://www.java.com/en/download/)
Download & install [Maven](https://maven.apache.org/install.html)

- verify step via two commands:  
`java -version`  
`mvn -version`


Writing a test
--------------
[Cucumber Ref Docs](https://docs.cucumber.io/cucumber/)

The cucumber feature files go in the `features` directory [here](https://github.com/mmmg/bdd-automation/tree/master/src/test/resources/features) and should have the ".feature" extension. 
There are 2 sub-directories setup for better organization:  
    - backend  
    - ui  

You can start out by looking at `features/my_first.feature`. You can extend this feature or make your own features using some of the [predefined steps](doc/canned_steps.md) that comes with selenium-cucumber.


Page objects design pattern
-----------------
The Page Object Pattern technique provides a solution for working with multiple web pages and prevents unwanted code duplication and enables an uncomplicated solution for code maintenance.   
[Page Object Design Pattern Info](http://toolsqa.com/selenium-cucumber-framework/page-object-design-pattern-with-selenium-pagefactory-in-cucumber/)   
Page objects are located [here](https://github.com/mmmg/bdd-automation/tree/master/src/main/java/com/medmen/bdd/pages)

Configuration Management
-----------------
There are 4 .properties files located [here](https://github.com/mmmg/bdd-automation/tree/master/src/main/resources): (default is stage.properties)  
    - local.properties    
    - dev.properties   
    - stage.properties  
    - prod.properties    
    
Each of these files is referenced in the EnvironmentConfig class    

Predefined steps & Helper Methods
-----------------
By using predefined steps you can automate your test cases more quickly, more efficiently and without much coding.

- The predefined steps are located [here](https://github.com/mmmg/bdd-automation/tree/master/src/test/java/com/medmen/bdd/stepDefs)  

The framework also has helper methods that can be used to navigate the UI of any page 

- The helper methods are located [here](https://github.com/mmmg/bdd-automation/tree/master/src/main/java/com/medmen/bdd/helperMethods)


Running tests
--------------

The `TestRunner` class is the brains of this framework, located [here](https://github.com/mmmg/bdd-automation/blob/master/src/test/java/com/medmen/bdd/runner/TestRunner.java)
to over ride it using the following command line args.


Go to the projects root directory from terminal and use the following commands / arguments:  
* ```mvn clean install``` (default will run on a local firefox browser)  
* ```-Dbrowser=chrome``` (to use a specific browser, current options: chrome, firefox, phantomjs)     
* ```-DbrowserLocation=remote``` (to run browser tests against a remote grid, default is set to local)  
* ```-Dheadless=true``` (to run browser tests in headless mode, default is set to false)
* ```-Denv=stage``` (to run tests against different environments current options: localhost, dev, stage, prod; default is stage)  
* ```-Dcucumber.options="--tags @tests"``` (to override the default tags set in the TestRunner class)    
Example:  
 ``` mvn clean install -Denv=prod -Dbrowser=firefox -Dheadless=true -Dcucumber.options="--tags @tests"```

CI - CircleCi is hte projects build tool
--------------
Located in the .circleci directory is the config.yml for the circle ci integration

- Reference [Info](https://circleci.com/docs/2.0/configuration-reference/)

- CircleCi URL [here](https://circleci.com/gh/mmmg/bdd-automation)