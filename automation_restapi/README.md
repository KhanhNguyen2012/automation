# Automation API Testing


## Environments
- [ ] Java JDK 11.
- [ ] Maven 3.8.4.
- [ ] [Repository of project](https://dev-gitlab:8089/automation/automation_restapi.git)
- [ ] Intellij IDEA

***

## Project structure

```
src
├───main
│   └───java
│       └───vn.com.fecredit.icollect
|            └───actor
|            └───authentication
|            └───cast
|            └───datatable
└───test
    ├───java
    │   └───starter
    │       └───constants
    │       └───dbunit
    │       └───restinteractions
    │       └───stepdefinitions
    │       └───tasks
    │       └───utils
    └───resources
        └───features
        └───files
```

## Feature file
Feature file is a file written in **Gherkin syntax** (.feature extension) that defines test scenarios in a human-readable format.
````
Feature: Test APIs
  Background:
    Given I'm "Nam"
    And I provide the database information
      | url                                     | username | password  | schema |
      | jdbc:postgresql://10.30.121.28:5444/crm | ticket   | ticketuat | ticket |
    And I prepare information of headers
      | header          | value                                           |
      | Accept          | */*                                             |
      | Accept-Language | en-US,en;q=0.9,vi;q=0.8                         |
      | Connection      | keep-alive                                      |
      | Content-Type    | application/x-www-form-urlencoded;charset:UTF-8 |

  Scenario: Verify API response for card payments information
    And I've prepare data
      | path                                              |
      | src/test/resources/files/call-api-with-params.txt |
    When I call service api "mcrticket" by "/mcrticket/services/vymoTicketService/getCardPaymentInfo" and method "POST" with parameters
      | body | params                                              |
      |      | aggId=20250303031500020000040439&date=1642736148206 |
    Then the response status is "200"
    And at "data.paymentInfo.lateFee" i'll see the value is "0.0"
    And at "data.paymentInfo.overlimitFee" i'll see the value is "0.0"
    And at "data.paymentInfo.insuranceFee" i'll see the value is "0.0"
    And at "data.paymentInfo.membershipFee" i'll see the value is "0.0"
    And I clean data
      | path                                              |
      | src/test/resources/files/call-api-with-params.txt |
````
Each line has to start with a *Gherkin keyword*, followed by any text.

### Feature
This keyword provide a description of a feature and group related scenarios.\
It starts with ``Feature``, followed by a ``:`` and short text that describes the feature.\
We can add free-form text underneath ``Feature`` to add more description.

### Scenario
This keyword provide a description of a test case and group related steps
It starts with ``Scenario``, followed by a ``:`` and short text that describes the feature of test case such as: *Verify information of product*,...

### Steps
Each step starts with ``Given``, ``When``, ``Then``, ``And``.

#### Given step
``Given`` steps are used to describe the initial context, necessary conditions, and data that need to be prepared before performing the test such as providing information of request header, information of connecting database, adding data to database. Steps we need to prepare before we start interacting with the system (in the ``When`` step).

#### When step
``When`` steps are used to describe an event or action directly related to current test case.

#### Then step
``Then`` steps are used to describe an expect result such as comparing the actual result to the expected result.

#### And step
``And`` is used to replace duplicate key steps.

#### DataTable
The data list is presented in a table format, providing accompanying information for that step.

#### Background
Sometimes the ``Given`` key will be repeated continuously in every scenario of a feature.\
This key help to summarize the common ``Given`` steps.\

## Step definitions files
This file is used to translate the steps in the ``.feature`` file into executable actions