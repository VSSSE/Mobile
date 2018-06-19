Feature: Open Look up movies page


  Scenario Outline: Open look up movie title activity
    Given I have a MainActivity
    When I press look up movies
    Then I should see instructions how to search movies
