package stepDefinitions.duobank;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Driver;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DuoStepDefs {

    String email;
    String pass;
    RequestSpecification requestSpecification;
    Response response;

    @Given("The base URI is set to {string}")
    public void the_base_uri_is_set_to(String uri) {

        baseURI = uri;

    }
    @Given("The valid body is added to the request")
    public void the_valid_body_is_added_to_the_request() {


        email =  new Faker().internet().emailAddress();
        pass = new Faker().internet().password();

        requestSpecification = given().
                                         body("{\n" +
                                                                    "\"first_name\" : \"Joe\",\n" +
                                                                    "\"last_name\" : \"Doe\",\n" +
                                                                    "\"email\" : \"" + email + "\",\n" +
                                                                    "\"password\" : \"" + pass + "\"\n" +
                                                                    "\n" +
                                                                    "}");


    }
    @When("I send a POST request to endpoint {string}")
    public void i_send_a_post_request_to_endpoint(String endpoint) {


       response = requestSpecification.when().log().all().
                                                  post(endpoint);


    }
    @Then("The status code should be {int} and response payload should contain a message {string}")
    public void the_status_code_should_be_and_response_payload_should_contain_a_message(Integer status, String message) {
        response.then(). log().all().
                        assertThat().
                        statusCode(status).
                        body("message", equalTo(message));



    }
    @Then("I navigate to the homepage")
    public void i_navigate_to_the_homepage() {
        // Verify the user creation through logging in with the same creadentials in the UI;

        Driver.getDriver().get("http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php");


    }
    @When("I enter the same credentials sent by API request")
    public void i_enter_the_same_credentials_sent_by_api_request() {
        Driver.getDriver().findElement(By.id("exampleInputEmail1")).sendKeys(email, Keys.TAB, pass, Keys.ENTER);


    }
    @Then("I should be able to login")
    public void i_should_be_able_to_login() {
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Loan Application"));

        Driver.getDriver().quit();
    }
}
