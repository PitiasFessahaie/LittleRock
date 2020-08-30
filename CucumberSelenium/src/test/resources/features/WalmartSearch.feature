Feature: Walmart

  @SmokeTest
  Scenario: website search bar functionality
    Given I open a browser
    When I navigate to Walmart website
    And I enter XBox in the search field
    And I click search button
    Then I should see XBox result

