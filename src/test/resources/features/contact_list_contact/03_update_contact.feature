@Contact
Feature: Update Contact

  Scenario: User can update the contact via API
    Given set the url for update contact
    And set the expected data for update contact
    When send the patch request for update contact
    Then do assertion for update contact