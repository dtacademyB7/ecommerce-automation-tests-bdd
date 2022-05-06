package stepDefinitions.duotifyStepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BusinessRuleStepDefs {

    List<String> actualColumnNames;
    @When("I send a query to retrieve column names for songs table")
    public void i_send_a_query_to_retrieve_column_names_for_songs_table() {

        actualColumnNames = DBUtility.getColumnNames("select * from songs");
    }
    @Then("the column names should be the following")
    public void the_column_names_should_be_the_following(List<String> expectedColumnames) {

        Assert.assertEquals(expectedColumnames, actualColumnNames);


    }


    String shareUSername;
    String sharedLastName;
    @When("I update the last name of the user with the username {string} with {string}")
    public void i_update_the_last_name_of_the_user_with_the_username_with(String username, String expectedLastName) throws SQLException {


       shareUSername = username;
       sharedLastName = expectedLastName;
        String query = "update users set lastName='"+expectedLastName+"' where username='"+username+"';";
        DBUtility.updateQuery(query);

    }
    @Then("The value should be updated correctly")
    public void the_value_should_be_updated_correctly() {

        String query = "select lastName from users where username='"+shareUSername+"';";
        List<List<Object>> listoflists = DBUtility.getQueryResultAsListOfLists(query);


        Assert.assertEquals(sharedLastName, (String)(listoflists.get(0).get(0)));
    }

    List<String> list2;
    @When("I send a quesry to retrieve all usernames")
    public void i_send_a_quesry_to_retrieve_all_usernames() {
         List<List<Object>> list = DBUtility.getQueryResultAsListOfLists("select username  from users");

         list2 = new ArrayList<>();
        for (List<Object> each : list) {
            list2.add((String)(each.get(0)));
        }

        Collections.sort(list2);

    }
    @Then("The usernames should not contain duplicates")
    public void the_usernames_should_not_contain_duplicates() {

        // could be done through sql query too -> select username, count(*)  from users group by username having count(*)>1

        boolean hasNoDuplicate = true;
        for (int i = 0; i < list2.size()-1; i++) {

            if(list2.get(i).equals(list2.get(i+1))){

                System.out.println(list2.get(i) + " " + list2.get(i+1));
                hasNoDuplicate = false;
               break;
            }
        }

        Assert.assertTrue(hasNoDuplicate);
    }


}
