package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DatatableDemoStepDefs {

    @Given("I set up something")
    public void i_set_up_something() {

    }
    @When("I pass this data as List")
    public void i_pass_this_data(List<String> dataTable) {
        System.out.println(dataTable);
    }
    @Then("I should see the expected result")
    public void i_should_see_the_expected_result() {

    }

    @When("I pass this data as List of lists")
    public void i_pass_this_data_as_list_of_lists(List<List<String>> dataTable) {
        System.out.println(dataTable);

        Assert.assertEquals(dataTable.get(1).get(1), "Dahle");
    }

    @When("I pass this data as List of Maps")
    public void i_pass_this_data_as_list_of_maps(List<Map<String,String>> dataTable) {
        System.out.println(dataTable);
        Assert.assertEquals(dataTable.get(1).get("lastName"), "Dahle");
    }

    @When("I pass this data as a Map")
    public void i_pass_this_data_as_a_map(Map<String, String> dataTable) {
        System.out.println(dataTable);

        System.out.println(dataTable.get("KSEA"));
    }

    @When("I pass this data as a Map<String, List<String>>")
    public void i_pass_this_data_as_a_map_string_list_string(Map<String, List<String>> dataTable) {

        System.out.println(dataTable);
    }


    @Given("I am connected to database")
    public void i_am_connected_to_database() {

    }
    @When("I send the following query")
    public void i_send_the_following_query(String docString) {
        System.out.println(docString);
    }
    @Then("the result should be correct")
    public void the_result_should_be_correct() {

    }



}
