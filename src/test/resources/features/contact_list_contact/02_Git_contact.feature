@Contact
Feature: Read Contact

  Scenario: Read the Created Contact
    Given set the URL for reading the created contact
    Given set the expected data for reading contact
    When send the GET request for reading the created contact
    Then do assertions for reading the created contact