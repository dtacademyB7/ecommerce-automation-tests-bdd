package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Homepage;
import pages.ProductPage;
import utilities.Driver;

public class ProductDetailsStepDefs {

    @When("I click on a product {string}")
    public void i_click_on_a_product(String productName) {
        Homepage homepage = new Homepage();
        homepage.clickOnAProduct(productName);
    }
    @When("I land on a product details page with title containing {string}")
    public void i_land_on_a_product_details_page_with_title_containing(String productName) {
        Assert.assertTrue(Driver.getDriver().getTitle().contains(productName));
    }
    @Then("The title of the product should be {string}")
    public void the_title_of_the_product_should_be(String productName) {
        ProductPage productPage = new ProductPage();
        Assert.assertEquals(productName, productPage.productTitle.getText());
    }

    @Then("The price of the product should be {double}")
    public void the_price_of_the_product_should_be(Double price) {
        ProductPage productPage = new ProductPage();
        Assert.assertEquals(price, Double.valueOf(productPage.productPrice.getText().replace("$", "")));
    }

    @Then("The default quantity should be {int}")
    public void the_default_quantity_should_be(Integer defQuantity) {

        ProductPage productPage = new ProductPage();
        Assert.assertEquals(defQuantity, Integer.valueOf(productPage.quantity.getAttribute("value")));
    }
    Integer sharedTimes;
    @When("I click on a plus button {int} times")
    public void i_click_on_a_plus_button_times(Integer times) {
        sharedTimes = times;
        ProductPage productPage = new ProductPage();
        productPage.clickOnPlusButton(times);

    }
    @Then("The quantity should be correct")
    public void the_quantity_should_be() {
        Integer actualQuantity = Integer.valueOf(new ProductPage().quantity.getAttribute("value"));
        Integer expectedQuantity = sharedTimes + 1;

        Assert.assertEquals(expectedQuantity, actualQuantity);
    }





    // Hardcoded step def methods, no longer needed after parametrization

//
//    @When("I click on a product Faded Short Sleeve T-shirts")
//    public void i_click_on_a_product_faded_short_sleeve_t_shirts() {
//        Homepage homepage = new Homepage();
//        homepage.product1.click();
//    }
//
//    @Then("I land on a product details page with title containing Faded Short Sleeve T-shirts")
//    public void someMethod(){
//        Assert.assertTrue(Driver.getDriver().getTitle().contains("Faded Short Sleeve T-shirts"));
//    }
//
//    @Then("The title of the product should be Faded Short Sleeve T-shirts")
//    public void the_title_of_the_product_should_be_the_same() {
//          ProductPage productPage = new ProductPage();
//          Assert.assertEquals("Faded Short Sleeve T-shirts", productPage.productTitle.getText());
//
//    }
//
//
//    @When("I click on a product Blouse")
//    public void i_click_on_a_product_blouse() {
//        Homepage homepage = new Homepage();
//        homepage.product2.click();
//    }
//    @When("I land on a product details page with title containing Blouse")
//    public void i_land_on_a_product_details_page_with_title_containing_blouse() {
//        Assert.assertTrue(Driver.getDriver().getTitle().contains("Blouse"));
//    }
//    @Then("The title of the product should be Blouse")
//    public void the_title_of_the_product_should_be_blouse() {
//        ProductPage productPage = new ProductPage();
//        Assert.assertEquals("Blouse", productPage.productTitle.getText());
//
//    }



    // Demo of the diff parameters

//    @Given("I have {int} {string}")
//    public void i_have(Integer amount, String fruit) {
//
//        System.out.println("the amount: " + amount);
//        System.out.println("the fruit: " + fruit);
//    }
//    @When("I take {int} away")
//    public void i_take_away(Integer amount) {
//        System.out.println("the taken amount: " + amount);
//    }
//    @Then("I should have {int} left and the price should be {double}")
//    public void i_should_have_left_and_the_price_should_be(Integer amount, Double price) {
//        System.out.println("the left amount: " + amount);
//        System.out.println("the  price: " + price);
//    }

}
