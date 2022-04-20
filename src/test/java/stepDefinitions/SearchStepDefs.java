package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.Homepage;
import pages.SearchResultsPage;
import utilities.Driver;
import utilities.PropertyReader;

public class SearchStepDefs {


    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Driver.getDriver().get(PropertyReader.getProperty("url"));
    }
    @When("I search for a Blouse")
    public void i_search_for_a_blouse() {
        Homepage homepage = new Homepage();
        homepage.searchBar.sendKeys("Blouse", Keys.ENTER);
    }
    @Then("I should see the Blouse in the search results")
    public void i_should_see_the_blouse_in_the_search_results() {
        SearchResultsPage searchResultsPage = new SearchResultsPage();
        Assert.assertTrue(searchResultsPage.blouseProduct.getText().contains("Blouse"));
        Driver.quitDriver();

//        Assert.assertEquals(, );
//
    }

}
