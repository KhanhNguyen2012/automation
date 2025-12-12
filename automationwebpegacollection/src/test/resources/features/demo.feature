@demoSearchFeature
Feature: DemoQA Search Interaction
  As a user
  I want to interact with DemoQA elements
  So that I can test web automation without CAPTCHA challenges

  Scenario: Navigate to DemoQA and verify page title
    Given I navigate to DemoQA homepage
    Then the page title should be "DEMOQA"
    And the page should display "Elements"

  Scenario: Search for book by title
    Given I navigate to the book store page
    When I search for book title "Git Pocket Guide"
    Then the search results should display books
    And the results should show at least one book
