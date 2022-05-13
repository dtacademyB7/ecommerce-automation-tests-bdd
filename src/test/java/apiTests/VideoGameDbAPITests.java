package apiTests;


//import org.hamcrest.Matchers;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Driver;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VideoGameDbAPITests {


    static{
        baseURI = "http://localhost:8080/app";
    }





      @Test
      public void testRestAssured(){

          // Rest Assured library can be easily used in TDD style test
          // It is important to add 3 static imports to easily write our test code


          // Rest Assured uses given-when-then style

          // Steps:


          // Set the BASE URI
          //RestAssured.baseURI = "http://localhost:8080/app";



          // given - request specifications are added here
          // headers, parameters, authorization, body


          // when - request type and the endpoint is passed here

          // then - the assertions are done here


          given().
                  header("Accept", "application/json").
                  pathParam("videoGameId", "4").
          when().log().all().
//                  get("/videogames/4").  // path parameter is hardcoded here
//                     get("http://localhost:8080/app/videogames/4").
                  get("/videogames/{videoGameId}").
          then().log().all().
                 assertThat().
                  statusCode(equalTo(200)).
                  body("id", equalTo(4)).
                  body("name", is("Super Mario 64")).
                  body("releaseDate", equalTo("1996-10-20")).
                  body("reviewScore", equalTo(90)).
                  header("Content-Type", equalTo("application/json")).
                  header("Content-Length", equalTo("119")).
                  header("Date", containsString("Wed, 11 May 2022")).
                  time(lessThan(3000L));




      }






    @Test
    public void testPOST(){

        int id =  100 + new Random().nextInt(1000);


        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body("{\n" +
                        "    \"id\": "+id+",\n" +
                        "    \"name\": \"Half Life\",\n" +
                        "    \"releaseDate\": \"1999-10-01\",\n" +
                        "    \"reviewScore\": 99,\n" +
                        "    \"category\": \"FPS\",\n" +
                        "    \"rating\": \"PG13\"\n" +
                        "}").
                when().log().all().
                        post("/videogames").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
//                header("Content-Type" , equalTo("application/json")).
                body("status", containsString("Successfully"));


    }


    @Test
    public void testPUT(){


        given().
                header("Accept", "application/json"). // I can accept/understand only json
                header("Content-Type", "application/json").
                body("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"name\": \"Need For Speed 3\",\n" +
                        "    \"releaseDate\": \"2010-03-10\",\n" +
                        "    \"reviewScore\": 99,\n" +
                        "    \"category\": \"Driving\",\n" +
                        "    \"rating\": \"Universal\"\n" +
                        "}").
                pathParam("videoGameId", "2").
        when().log().all().
                    put("/videogames/{videoGameId}").
        then().log().all().

                    statusCode(is(200)).
                    body("id", equalTo(2)).
                    header("Content-Type", "application/json");



    }

    @Test
    public void testDELETE(){

        given().
                header("Accept", "application/json").


                pathParam("videoGameId", "2").
                when().log().all().
                delete("/videogames/{videoGameId}").
                then().log().all().

                statusCode(is(200)).
                body("status", equalTo("Record Deleted Successfully")).
                header("Content-Type", "application/json");
    }




    @Test
    public void testEndToendFlow(){


        // Create a new videogame with random id

        int id =  100 + new Random().nextInt(1000);


        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body("{\n" +
                        "    \"id\": "+id+",\n" +
                        "    \"name\": \"Half Life\",\n" +
                        "    \"releaseDate\": \"1999-10-01\",\n" +
                        "    \"reviewScore\": 99,\n" +
                        "    \"category\": \"FPS\",\n" +
                        "    \"rating\": \"PG13\"\n" +
                        "}").
                when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
//                header("Content-Type" , equalTo("application/json")).
                 body("status", containsString("Successfully"));






        // Verify if it is created via GET request

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().
//
                get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
                body("id", equalTo(id));



        // Update the same video game with new name

        String gameName = new Faker().rockBand().name();
        given().
                header("Accept", "application/json"). // I can accept/understand only json
                header("Content-Type", "application/json").
                body("{\n" +
                        "    \"id\": "+id+",\n" +
                        "    \"name\": \""+gameName+"\",\n" +
                        "    \"releaseDate\": \"2010-03-10\",\n" +
                        "    \"reviewScore\": 99,\n" +
                        "    \"category\": \"Driving\",\n" +
                        "    \"rating\": \"Universal\"\n" +
                        "}").
                pathParam("videoGameId", id).
                when().log().all().
                put("/videogames/{videoGameId}").
                then().log().all().

                statusCode(is(200)).
                body("id", equalTo(id)).
                header("Content-Type", "application/json");

        // Verify that the update happened with GET

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().

        get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
                body("name", equalTo(gameName));



        // Delete the same video game

        given().
                header("Accept", "application/json").


                pathParam("videoGameId", id).
                when().log().all().
                delete("/videogames/{videoGameId}").
                then().log().all().

                statusCode(is(200)).
                body("status", equalTo("Record Deleted Successfully")).
                header("Content-Type", "application/json");


        // Verify with GET

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().

                get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(equalTo(500));


    }


      @Test
      public void testMultiLayerAPItoUI(){

        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

        String email =  new Faker().internet().emailAddress();
        String pass = new Faker().internet().password();
        given().
                body("{\n" +
                        "\"first_name\" : \"Joe\",\n" +
                        "\"last_name\" : \"Doe\",\n" +
                        "\"email\" : \""+email+"\",\n" +
                        "\"password\" : \""+pass+"\"\n" +
                        "\n" +
                        "}").
                when(). log().all().
                post("/register.php").
                then(). log().all().
                assertThat().
                statusCode(200).
                body("status", equalTo(201)).
                body("message", equalTo("You have successfully registered."));


           // Verify the user creation through logging in with the same creadentials in the UI;

          Driver.getDriver().get("http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php");

          Driver.getDriver().findElement(By.id("exampleInputEmail1")).sendKeys(email, Keys.TAB, pass, Keys.ENTER);

          Assert.assertTrue(Driver.getDriver().getTitle().contains("Loan Application"));

          Driver.getDriver().quit();


      }





      @Test
      public void extractValuesFromResponse(){

      }




}
