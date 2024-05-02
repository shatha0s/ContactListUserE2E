@Contact
Feature: Read Nonexistent Contact

  Scenario: Read a Nonexistent Contact
    Given set the URL for reading the Nonexistent contact
    When send the GET request for reading the created contact using id
    Then message should be faild