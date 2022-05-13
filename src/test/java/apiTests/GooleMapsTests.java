package apiTests;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GooleMapsTests {

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
    public void extractValuesFromResponse(){

        baseURI = "https://maps.googleapis.com/maps/api";


        JsonPath responseBodyAsJsonPath = given().
                pathParam("type", "json").
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").


                when().log().all().
                get("place/findplacefromtext/{type}").
                then().log().all().
                statusCode(200).extract().jsonPath();

        // JsonPath class uses Groovy GPath behind the scenes which is similar to Xpath
        String placeId = responseBodyAsJsonPath.getString("candidates[0].place_id");

        System.out.println(placeId);

    }
}
