Feature: Open Look up soundtracks page


  Scenario Outline: Open look up soundtracks activity
    Given I have a MainActivity
    When I press look up soundtracks
    Then I should see instructions how to search soundtracks
