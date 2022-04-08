Feature: I want to test task six

  Scenario: I want to login with correct credentials
    Given I go to main page
    When I login as "bugofnet" with password "1234Abcd@"
    Then I have been successfully logged
