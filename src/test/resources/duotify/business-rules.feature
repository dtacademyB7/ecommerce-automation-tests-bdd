Feature: Business logic and rules



  Scenario: Verify songs table column names

    When I send a query to retrieve column names for songs table
    Then the column names should be the following
      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |


    Scenario: Verify Unicode support
      When I update the last name of the user with the username "duotech" with "平仮名"
      Then The value should be updated correctly

  @db
  Scenario: Verify business logic for duplicates
    When I send a quesry to retrieve all usernames
    Then The usernames should not contain duplicates

