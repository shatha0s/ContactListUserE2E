@Contact
Feature: Delete contact

  Scenario: User can delete the contact via API
    Given set the url for delete contact
    When send the delete request contact
    Then do assertion for delete contact