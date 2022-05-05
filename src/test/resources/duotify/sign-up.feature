Feature: Sign up features



  Scenario: Verify user sign up

    Given I am on the duotify homepage
    When I sign up using valid credentials
    Then I should be able to land on the homepage
    And I should be able to verify the user details in the database


  @db
  Scenario: Verify user sign up using DataTable

    Given I am on the duotify homepage
    When I sign up using the following credentials
      | username     | first  | last  | email                  | password    |
      | donnie.darko | Donnie | Darko | donnie.darko@gmail.com | donnie12345 |
    Then I should be able to land on the homepage
    And I should be able to verify the user details in the database