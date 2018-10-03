# Medmen BDD Automation

Selenium-cucumber: Automation Testing Using Java

Selenium-cucumber is a behavior driven development (BDD) approach to write automation test script to test Web.
It enables you to write and execute automated acceptance/unit tests.

Writing a test
--------------

The cucumber features goes in the `features` library and should have the ".feature" extension.

You can start out by looking at `features/my_first.feature`. You can extend this feature or make your own features using some of the [predefined steps](doc/canned_steps.md) that comes with selenium-cucumber.


Page objects design pattern
-----------------
The Page Object Pattern technique provides a solution for working with multiple web pages and prevents unwanted code duplication and enables an uncomplicated solution for code maintenance.   
[Page Object Design Pattern Info](http://toolsqa.com/selenium-cucumber-framework/page-object-design-pattern-with-selenium-pagefactory-in-cucumber/)   
Page objects are located [here](dhttps://bitbucket.org/lucienminot/medmen-bdd-automation/src/master/src/test/java/com/medmen/bdd/stepDefs/PredefinedStepDefinitions.java)


Predefined steps
-----------------
By using predefined steps you can automate your test cases more quickly, more efficiently and without much coding.

The predefined steps are located [here](dhttps://bitbucket.org/lucienminot/medmen-bdd-automation/src/master/src/test/java/com/medmen/bdd/stepDefs)


Running test
--------------

Go to your project directory from terminal and hit following commands:  
* ```mvn test``` (defualt will run on local firefox browser)  
* ```mvn test -Dbrowser=chrome``` (to use any other browser, current options: chrome, firefox, phantomjs)     
* ```mvn test -DbrowserLocation=remote``` (to run test on a remote grid, default is set to local)  
* ```mvn test -Dheadless=true``` (to run test browser in headless mode, default is set to false)  
Example:  
 ```mvn clean install -Dbrowser=firefox -Dheadless=true```

