package apiTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SerializationDeserialization {




    @Test
    public void serializeUsingMap(){


        baseURI = "http://localhost:8080/app";


        int id =  100 + new Random().nextInt(1000);



        Map<String,Object> videoGameMap = new HashMap<>();
        videoGameMap.put("id",  id);
        videoGameMap.put("releaseDate", "1984-06-25");
        videoGameMap.put("reviewScore", 88);
        videoGameMap.put("category", "Puzzle");
        videoGameMap.put("rating", "Universal");

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body(videoGameMap).
                // serialization/de-serialization is done automatically by built in libraries like Jackson, Gson
                when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
//                header("Content-Type" , equalTo("application/json")).
        body("status", containsString("Successfully"));


    }


    @Test
    public void serializeUsingPOJO(){


        baseURI = "http://localhost:8080/app";


        int id =  100 + new Random().nextInt(1000);


        VideoGame videoGame =  new VideoGame(
                id,
                "Half-Life",
                "1999-09-03",
                99,
                "FPS",
                "PG13");



        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body(videoGame).
                // serialization/de-serialization is done automatically by built in libraries like Jackson, Gson
                        when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
//                header("Content-Type" , equalTo("application/json")).
        body("status", containsString("Successfully"));


    }


    @Test
    public void deserializeUsingMap(){


        baseURI = "http://localhost:8080/app";


        VideoGame videoGame =  new VideoGame(
                5,
                "Half-Life",
                "1999-09-03",
                99,
                "FPS",
                "PG13");

        Map map = given().
                header("Accept", "application/json"). // I can accept/understand only json
                        header("Content-Type", "application/json").
                body(videoGame).
                pathParam("videoGameId", "5").
                when().log().all().
                put("/videogames/{videoGameId}").
                then().log().all().

                statusCode(is(200)).

                header("Content-Type", "application/json").extract().as(Map.class);


        System.out.println(map);

        System.out.println(map.get("name"));



    }


    @Test
    public void deserializeUsingPOJO(){


        baseURI = "http://localhost:8080/app";


        VideoGame videoGame =  new VideoGame(
                5,
                "Half-Life",
                "1999-09-03",
                99,
                "FPS",
                "PG13");

        VideoGame responseAsVideoGameObj = given().
                header("Accept", "application/json"). // I can accept/understand only json
                        header("Content-Type", "application/json").
                body(videoGame).
                pathParam("videoGameId", "5").
                when().log().all().
                put("/videogames/{videoGameId}").
                then().log().all().

                statusCode(is(200)).

                header("Content-Type", "application/json").extract().as(VideoGame.class);


        System.out.println(responseAsVideoGameObj);

        System.out.println(responseAsVideoGameObj.getName());


    }

    @Test
    public void deserializeUsingList(){


//        baseURI = "http://localhost:8080/app";
//        List list =  given().
//                header("Accept", "application/json").
//                when().log().all().
//
//        get("/videogames").
//                then().log().all().
//                assertThat().
//                statusCode(equalTo(200)).extract().as(List.class);  // Raw list type
//
//
//          Map<String, Object> game = (Map<String, Object>) list.get(0);
//
//          System.out.println(game.get("id"));

        baseURI = "http://localhost:8080/app";
        given().
                header("Accept", "application/json").
                when().log().all().

                get("/videogames").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).extract().as( new TypeRef<List<Map<String,Object>>>(){} );


//        Map<String, Object> game = (Map<String, Object>) list.get(0);
//
//        System.out.println(game.get("id"));

    }



}
