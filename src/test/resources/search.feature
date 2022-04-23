#@search  tags can be added on a feature which is applied to all scenarios of the feature


Feature:Search Bar feature


  Background: Common steps for all scenarios
    Given I am on the homepage


   @search @product @blouse @temp
  Scenario: Product search
    When I search for a Blouse
    Then I should see the Blouse in the search results


  @search @product @temp
  Scenario: Product search 2
    When I search for a Printed Dress
    Then I should see the Printed Dress in the search results

  @search @printedSummer
  Scenario: Product search 3
    When I search for a Printed Summer Dress
    Then I should see the Printed Summer Dress in the search results