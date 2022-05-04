package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ProductPage;
import pages.ShoppingCartPage;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class ShoppingCartStepDefs {

    int expectedQuantuty;
    String expColor;
    String expSize;
    @When("I increase the quantity {int} and choose the size {string} and color {string} and click on add to cart")
    public void i_increase_the_quantity_and_choose_the_size_and_color_and_click_on_add_to_cart(Integer quantity, String size, String color) {
        expectedQuantuty = quantity;
        expSize= size;
        expColor = color;

        ProductPage productPage = new ProductPage();

        productPage.clickOnPlusButton(quantity-1);
        productPage.chooseSize(size);
        productPage.chooseColor(color);
        productPage.submitButton.click();
        SeleniumUtils.waitFor(3);

    }
    @Then("The quantity, size and color should be correct")
    public void the_quantity_size_and_color_should_be_correct() {

        ProductPage productPage = new ProductPage();
        int actualQuantity = Integer.parseInt(productPage.popUpQuantity.getText());
        String[] items = productPage.popUpAttributes.getText().split(",");
        String actualColor =  items[0].trim();
        String actualSize = items[1].trim();

        Assert.assertEquals(expectedQuantuty, actualQuantity);
        Assert.assertEquals(expColor, actualColor);
        Assert.assertEquals(expSize , actualSize);

        productPage.nextButton.click();


    }
    @Then("The shopping cart details should be the following")
    public void the_shopping_cart_details_should_be_the_following(List<Map<String,String>> dataTable) {

      Map<String,String> map = dataTable.get(0);
        ShoppingCartPage shoppingCartPage =  new ShoppingCartPage();
        Assert.assertEquals(map.get("Name"), shoppingCartPage.productName.getText()  );
        Assert.assertEquals(map.get("Quantity"), shoppingCartPage.quantity.getText().split(" ")[0]  );
        Assert.assertEquals(map.get("Unit Price"), shoppingCartPage.unitPrice.getText() );
        Assert.assertEquals(map.get("TotalBeforeShipping"), shoppingCartPage.totalBeforeShipping.getText().trim()  );
        Assert.assertEquals(map.get("Shipping Fee"), shoppingCartPage.shippingFee.getText() );
    }


//    @Given("I am on the homepage")
//    public void i_am_on_the_homepage() {
//        Driver.getDriver().get(PropertyReader.getProperty("url"));
//    }



    @When("do something on the page")
    public void do_something_on_the_page() {

    }
    @Then("I should see the value {word}")
    public void i_should_see_the_value_blouse() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see the value Blouse")
    public void i_should_see_the_value_blousesad() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
