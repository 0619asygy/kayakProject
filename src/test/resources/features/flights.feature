Feature: booking a flight

@chooseFlight
  Scenario: book a flight
    Given user is on the flights page
    Then user enters "Los Angles" for departure
    And user enters "Houston" for destination
    Then user picks a date as "May 7 2020" for departure
    And user picks a date as "June 7 2020" for return
    Then user clicks on search button
