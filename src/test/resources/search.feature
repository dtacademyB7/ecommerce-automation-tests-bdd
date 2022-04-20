Feature:Search Bar feature


  Scenario: Product search
    Given I am on the homepage
    When I search for a Blouse
    Then I should see the Blouse in the search results