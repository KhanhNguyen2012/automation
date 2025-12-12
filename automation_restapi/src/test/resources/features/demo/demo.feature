@Demo
Feature: Test RESTful API (https://restful-api.dev/)
  As a QA Engineer
  I want to test the RESTful API endpoints
  So that I can verify API functionality for CRUD operations

  Scenario Outline: Fetch demo successful
    Given I'm "<name>"
    Given I prepare information of request headers
      | header          | value                   |
      | Accept          | */*                     |
      | Accept-Language | en-US,en;q=0.9,vi;q=0.8 |
      | Connection      | keep-alive              |
      | Content-Type    | application/json        |

    When I call api "/objects/7" and method "GET"
      | body | params |
      |      |        |
    Then the response status is "200"
    * at "id" i'll see the value is not empty
    * at "name" i'll see the value is "Apple MacBook Pro 16"

    Examples:
      | name  |
      | Admin |