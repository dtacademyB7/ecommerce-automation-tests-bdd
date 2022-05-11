package apiTests;


//import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo {






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

          when().log().all().
                  get("/videogames/4").
          then().log().all().
                 assertThat().
                  statusCode(equalTo(200)).
                  body("id", equalTo(4)).
                  body("name", is("Super Mario 64")).
                  body("releaseDate", equalTo("1996-10-20")).
                  body("reviewScore", equalTo(89));



      }

}
