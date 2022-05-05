package stepDefinitions.duotifyStepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import pages.duotifyPages.SignUpPage;
import utilities.DBUtility;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SignUpStepDefs {


    String username;
    @Given("I am on the duotify homepage")
    public void i_am_on_the_duotify_homepage() {

        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");


    }

    String expectedFirst;
    String expectedLast;
    String expectedEmail;
    String expectedPassword;


    @When("I sign up using valid credentials")
    public void i_sign_up_using_valid_credentials() {

        Faker faker =  new Faker();

        SignUpPage signUpPage = new SignUpPage();

        signUpPage.signUpLink.click();
//        SeleniumUtils.waitFor(2);
        username = faker.name().username();
        signUpPage.username.sendKeys(username);
        expectedFirst = faker.name().firstName();
        signUpPage.firstName.sendKeys(expectedFirst);
        expectedLast = faker.name().lastName();
        signUpPage.lastName.sendKeys(expectedLast);

       expectedEmail = faker.internet().emailAddress();
        signUpPage.email.sendKeys(expectedEmail);
        signUpPage.email2.sendKeys(expectedEmail);
        expectedPassword = faker.internet().password();
        signUpPage.password.sendKeys(expectedPassword);
        signUpPage.password2.sendKeys(expectedPassword);
        signUpPage.registerButton.click();



    }

    @When("I sign up using the following credentials")
    public void i_sign_up_using_the_following_credentials(List<Map<String,String>> dataTable) {

           Map<String, String> map = dataTable.get(0);

        SignUpPage signUpPage = new SignUpPage();

        signUpPage.signUpLink.click();
//        SeleniumUtils.waitFor(2);
        username = map.get("username");
        signUpPage.username.sendKeys(username);
        expectedFirst = map.get("first");;
        signUpPage.firstName.sendKeys(expectedFirst);
        expectedLast = map.get("last");;
        signUpPage.lastName.sendKeys(expectedLast);

        expectedEmail = map.get("email");;
        signUpPage.email.sendKeys(expectedEmail);
        signUpPage.email2.sendKeys(expectedEmail);
        expectedPassword = map.get("password");;
        signUpPage.password.sendKeys(expectedPassword);
        signUpPage.password2.sendKeys(expectedPassword);
        signUpPage.registerButton.click();


    }
    @Then("I should be able to land on the homepage")
    public void i_should_be_able_to_land_on_the_homepage() {
        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
    }
    @Then("I should be able to verify the user details in the database")
    public void i_should_be_able_to_verify_the_user_details_in_the_database() throws SQLException {

        String query = "select * from users where username='"+username+"'";
        System.out.println(query);
        List<Map<String, Object>> listofmaps = DBUtility.getQueryResultListOfMaps(query);

        Map<String, Object > map = listofmaps.get(0);



        Assert.assertEquals(username, map.get("username"));
        Assert.assertEquals(expectedFirst, map.get("firstName"));
        Assert.assertEquals(expectedLast, map.get("lastName"));
        Assert.assertEquals(expectedEmail, map.get("email").toString().toLowerCase());

//        System.out.println(DigestUtils.md5Hex("61gnnja7"));
        Assert.assertEquals(DigestUtils.md5Hex(expectedPassword), map.get("password"));

        String updateQuery = "delete from users where username='"+username+"'";
        DBUtility.updateQuery(updateQuery); // clean the database by removing the user we just created from the ui

    }
}
