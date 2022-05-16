package apiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class Authentication {


    static {
        baseURI = "https://maps.googleapis.com/maps/api";
    }



    @Test
    public void testAPIKEy() {
     given().
                pathParam("type", "json").
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                // API key is static

                when().log().all().
                get("place/findplacefromtext/{type}").
                then().log().all().
                statusCode(200);



    }


    @Test
    public void basicAuth(){


        String basicAuth = Base64.getEncoder().encodeToString("postman:password".getBytes());

        given().
//                auth().
////                 basic("postman", "password").
//                 header("Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==").
                header("Authorization", "Basic " + basicAuth).
        when().log().all().
                get("https://postman-echo.com/basic-auth").
        then().log().all().
                statusCode(200).
                body("authenticated", equalTo(true));
    }

}
