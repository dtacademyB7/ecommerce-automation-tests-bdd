package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.Homepage;
import pages.ProductPage;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class HomepageStepDefs {

    @Then("The promoted products should be the following")
    public void the_promoted_products_should_be_the_following(List<String> expectedProductNames) {

        List<String> actualProductNames = SeleniumUtils.getElementsText(new Homepage().products);

        Assert.assertEquals(expectedProductNames, actualProductNames);
    }


    @Then("The product details should be the following as list of lists")
    public void the_product_details_should_be_the_following(List<List<String>> dataTable) {
        ProductPage productPage  = new ProductPage();

        String actualPrice = productPage.productPrice.getText().replace("$", "");
        String actualName = productPage.productTitle.getText();
        String actualModel = productPage.productModel.getText();
        String actualComposition= productPage.productComposition.getText();
        String actualStyle = productPage.productStyle.getText();
        String actualProperty = productPage.productProperty.getText();

        // Cucumber does not have soft assertions since it uses junit natively
        // If you want to implement it, you can use AssertJ library
        List<String> expectedRow = dataTable.get(1);
        Assert.assertEquals(expectedRow.get(0), actualName);
        Assert.assertEquals(expectedRow.get(1), actualPrice);
        Assert.assertEquals(expectedRow.get(2), actualModel);
        Assert.assertEquals(expectedRow.get(3), actualComposition);
        Assert.assertEquals(expectedRow.get(4), actualStyle);
        Assert.assertEquals(expectedRow.get(5), actualProperty);

        System.out.println(dataTable);



    }

    @Then("The product details should be the following as list of maps")
    public void the_product_details_should_be_the_following_as_list_of_maps(List<Map<String, String>> dataTable ) {

        ProductPage productPage  = new ProductPage();

        String actualPrice = productPage.productPrice.getText().replace("$", "");
        String actualName = productPage.productTitle.getText();
        String actualModel = productPage.productModel.getText();
        String actualComposition= productPage.productComposition.getText();
        String actualStyle = productPage.productStyle.getText();
        String actualProperty = productPage.productProperty.getText();

        // Cucumber does not have soft assertions since it uses junit natively
        // If you want to implement it, you can use AssertJ library
        Map<String, String> firstRow = dataTable.get(0);
        Assert.assertEquals(firstRow.get("Name"), actualName);
        Assert.assertEquals(firstRow.get("Price"), actualPrice);
        Assert.assertEquals(firstRow.get("Model"), actualModel);
        Assert.assertEquals(firstRow.get("Composition"), actualComposition);
        Assert.assertEquals(firstRow.get("Style"), actualStyle);
        Assert.assertEquals(firstRow.get("Properties"), actualProperty);


//        System.out.println(dataTable.get(1).get("Composition"));

        System.out.println(dataTable);
    }



}
