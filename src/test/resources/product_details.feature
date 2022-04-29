Feature: Product details



#  Scenario: Verify product title
#    Given I am on the homepage
#    When I click on a product Faded Short Sleeve T-shirts
#    And I land on a product details page with title containing Faded Short Sleeve T-shirts
#    Then The title of the product should be Faded Short Sleeve T-shirts
#
#
#  @productDetails @module1
#  Scenario: Verify product title 2
#    Given I am on the homepage
#    When I click on a product Blouse
#    And I land on a product details page with title containing Blouse
#    Then The title of the product should be Blouse

  @temp
  Scenario: Verify product title 3 using parametrization
    Given I am on the homepage
    When I click on a product "Blouse"
    And I land on a product details page with title containing "Blouse"
    Then The title of the product should be "Blouse"
    Then The price of the product should be 27.00


    @product @param @fail
  Scenario: Verify product title 3 using parametrization
    Given I am on the homepage
    When I click on a product "Faded Short Sleeve T-shirts"
    And I land on a product details page with title containing "Faded Short Sleeve T-shirts"
    Then The title of the product should be "Faded Short Sleeve T-shirts"
    Then The price of the product should be 16.52

  @product @param  @fail
  Scenario: Verify product title 3 using parametrization
    Given I am on the homepage
    When I click on a product "Printed Summer Dress"
    And I land on a product details page with title containing "Printed Summer Dress"
    Then The title of the product should be "Printed Summer Dress"
    Then The price of the product should be 28.99


  @product @quantity  @fail
  Scenario: Verify product default quantity
    Given I am on the homepage
    When I click on a product "Printed Chiffon Dress"
    Then The default quantity should be 1

  @product @plus  @fail
  Scenario: Verify quantity increase with plus button
    Given I am on the homepage
    When I click on a product "Printed Chiffon Dress"
    And I click on a plus button 20 times
    Then The quantity should be correct


     @docstring
    Scenario: Demo of passing multiline text (docstring) into a step
      Given I am connected to database
      When I send the following query
          """

           SELECT '%c%' as Chapter,
            SUM(CASE WHEN ticket.status IN ('new','assigned') THEN 1 ELSE 0 END) as `New`,
            ...
            SUM(CASE WHEN ticket.status='closed' THEN 1 ELSE 0 END) as 'Closed',
            count(id) AS Total,
            ticket.id AS _id
            FROM engine.ticket
            INNER JOIN engine.ticket_custom
            ON ticket.id = ticket_custom.ticket
            WHERE ticket_custom.name='chapter'
            AND ticket_custom.value LIKE '%c%'
            AND type='New material'
            AND milestone='1.1.12'
            AND component NOT LIKE 'internal_engine'
            GROUP BY ticket.id;
        """
      Then the result should be correct







#  Scenario vs Scenario Outline
#


  Scenario Outline: Verify quantity increase with plus button with different values
    Given I am on the homepage
    When I click on a product "<product>"
    And I click on a plus button <amount> times
    Then The quantity should be correct

    Examples:
      | amount | product                     |
      | 1      | Printed Dress               |
      | 20     | Printed Summer Dress        |
      | 50     | Printed Chiffon Dress       |
      | 100    | Blouse                      |
      | 500    | Faded Short Sleeve T-shirts |

#     demo of the diff types of parameters
#    @temp
#    Scenario: Demo the parametrization
#      Given I have 55 "cukes"
#      When I take 55 away
#      Then I should have 0 left and the price should be 5.99
#
#  @temp
#  Scenario: Demo the parametrization
#    Given I have 45 "watermelons"
#    When I take 20 away
#    Then I should have 25 left and the price should be 10.99