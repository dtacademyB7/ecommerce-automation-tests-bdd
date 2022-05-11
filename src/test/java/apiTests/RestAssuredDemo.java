package apiTests;


//import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo {


//    static{
//        baseURI = "http://localhost:8080/app";
//    }





      @Test
      public void testRestAssured(){

          // Rest Assured library can be easily used in TDD style test
          // It is important to add 3 static imports to easily write our test code


          // Rest Assured uses given-when-then style

          // Steps:


          // Set the BASE URI
          //RestAssured.baseURI = "http://localhost:8080/app";
          baseURI = "http://localhost:8080/app";


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
    public void testRestAssuredComplexResponse(){

          baseURI = "https://maps.googleapis.com/maps/api";


          given().
                  pathParam("type", "json").
                  queryParam("input", "Duotech Academy").
                  queryParam("inputtype", "textquery").
                  queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                  queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").

          when().log().all().
                  get("place/findplacefromtext/{type}").
          then(). log().all().
                  statusCode(200).
                  body("candidates[0].formatted_address", equalTo("2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States")).
                  body("candidates[0].geometry.location.lat", equalTo(38.878937F)).
                  body("candidates[0].rating", equalTo(5)).
                  body("candidates[0].photos[0].html_attributions[0]", equalTo("<a href=\"https://maps.google.com/maps/contrib/106148087424852379805\">A Google User</a>"));
//
//            candidates[0].formatted_address  -> Groovy Gpath syntax
    }



    @Test
    public void testPOST(){

          int id =  100 + new Random().nextInt(1000);
        baseURI = "http://localhost:8080/app";

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

}
