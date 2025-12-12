# AutomationWebPegaCollection

## Getting started

## Add your files

## Run Application
### 1. Download new web driver
- [Download Web driver](https://googlechromelabs.github.io/chrome-for-testing/)

### 2. Change the path of driver
- Change the path of <b><i>WINDOW_CHROME_DRIVER</i></b> variable in <b>DriverConstants.java</b> file
```
public static final String WINDOW_CHROME_DRIVER = "<path to chromedriver.exe>";
```

### 3. Change default driver
- Change default value of type to 'chrome' in <b>SerenityConfig.java</b>
```
String type = Objects.toString(System.getProperty("driver"), "chrome");
```

### 4. run Application
- Run command
```
mvn clean verify
```
***

## Run only the scenarios you want instead of all of them 
### 1. Add Tag config in CucumberTestSuite.java
- You can add tag filters so that the application only runs tagged scenarios.
```
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@slow")
```

### 2. add tag for features or scenario in .feature file
```
@slow
Feature: ....

or

@slow
Scenario: ....
```
