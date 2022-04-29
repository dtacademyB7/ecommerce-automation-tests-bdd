Feature: Homepage related functionalities



  Scenario: Verify homepage product names
    Given I am on the homepage
    Then The promoted products should be the following
      | Faded Short Sleeve T-shirts |
      | Blouse                      |
      | Printed Dress               |
      | Printed Dress               |
      | Printed Summer Dress        |
      | Printed Summer Dress        |
      | Printed Chiffon Dress       |
#    Cucumber DataTable is a convenient way to pass a set of data into a SINGLE step

  @fail
  Scenario: Verify homepage product details
    Given I am on the homepage
    When  I click on a product "Blouze"
    Then The product details should be the following as list of lists
      | Name   | Price | Model  | Composition | Style  | Properties   |
      | Blouse | 27.00 | demo_2 |  Cotton     | Casual | Short Sleeve |



  Scenario: Verify homepage product details using list of maps
    Given I am on the homepage
    When  I click on a product "Blouse"
    Then The product details should be the following as list of maps
      | Name   | Price | Model  | Composition | Style  | Properties   |
      | Blouse | 27.00 | demo_2 |  Cotton     | Casual | Short Sleeve |
#      | Example| 34.00 | dascsa |  Chiffon    | vds    | dscsd        |


  Scenario Outline: Verify homepage product details with multiple products
    Given I am on the homepage
    When  I click on a product "<product>"
    Then The product details should be the following as list of maps
      | Name      | Price   | Model   | Composition | Style   | Properties |
      | <product> | <price> | <model> | <material>  | <style> | <property> |

    Examples:
      | product                     | price | model  | material  | style  | property       |
      | Blouse                      | 27.00 | demo_2 | Cotton    | Casual | Short Sleeve   |
      | Faded Short Sleeve T-shirts | 16.55 | demo_1 | Cotton    | Casual | Short Sleeve   |
      | Printed Dress               | 26.00 | demo_3 | Cotton    | Girly  | Colorful Dress |
      | Printed Summer Dress        | 28.98 | demo_5 | Viscose   | Casual | Maxi Dress     |
      | Printed Chiffon Dress       | 16.40 | demo_7 | Polyester | Girly  | Midi Dress     |