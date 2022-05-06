Feature: Sign up features



  Scenario: Verify user sign up

    Given I am on the duotify homepage
    When I sign up using valid credentials
    Then I should be able to land on the homepage
    And I should be able to verify the user details in the database



  Scenario: Verify user sign up using DataTable

    Given I am on the duotify homepage
    When I sign up using the following credentials
      | username     | first  | last  | email                  | password    |
      | donnie.darko | Donnie | Darko | donnie.darko@gmail.com | donnie12345 |
    Then I should be able to land on the homepage
    And I should be able to verify the user details in the database



  Scenario Outline: Verify user sign up using Sceanrio outline

    Given I am on the duotify homepage
    When I sign up using the following credentials
      | username     | first  | last  | email                  | password    |
      | <username> | <first> | <last> | <email>| <password> |
    Then I should be able to land on the homepage
    And I should be able to verify the user details in the database


    Examples:
      | username     | first      | last       | email                     | password          |
      | donnie.darko | Donnie     | Darko      | donnie.darko@gmail.com    | donnie12345       |
      | imaudsley0   | Irena      | Maudsley   | imaudsley0@abc.net.au     | UeIeIAD5Le7P4Ct04 |
      | gaireton1    | Georgianna | Aireton    | gaireton1@ovh.net         | IsUfchHHbaEpzn    |
      | smanton2     | Sybilla    | Manton     | smanton2@slate.com        | p5CJd26aqIVl      |
      | mhandsheart3 | Melvyn     | Handsheart | mhandsheart3@addtoany.com | fojYTrEIMLrx      |


  @db
  Scenario: Verify user sign up flow from DB to UI

    Given I create a new user in the Database with the following details
      | username  | first | last | email           | password   |
      | danny.dog | Danny | Dog  | danny@gmail.com | danny12345 |
    When I login with the same credentials on the UI
    Then I should be able to land on the homepage
    And firstname, lastname and email should be correct


