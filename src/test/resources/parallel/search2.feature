#@search  tags can be added on a feature which is applied to all scenarios of the feature


Feature:Search Bar feature


  Background: Common steps for all scenarios
    Given I am on the homepage


   @search
  Scenario: Product search
    When I search for a Blouse
    Then I should see the Blouse in the search results

