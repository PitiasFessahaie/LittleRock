Feature: Search

  @single
  Scenario: Successful search item
    Given I open chrome browser
    When I navigate to google page
    And I enter laptop in the search field
    And I click on search button
    Then I should see search results

  @multiple
  Scenario Outline: Successful search items
    Given I open chrome browser
    When I navigate to google page
    And I enter "<items>" in the search field
    And I click on search button
    Then I should see search results with "<expected>"
    

    Examples:
      | items | expected |
      | rose | fail |
      | iphone | iphone |