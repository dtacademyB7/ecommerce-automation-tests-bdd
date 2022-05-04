package utilities;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDBUtils {


    public static void main(String[] args) throws SQLException {




        DBUtility.createConnection();

        System.out.println("Connection established");
        
        String query = "SELECT * from users limit 10";

        List<List<Object>> queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists(query);


        for (List<Object> eachRow : queryResultAsListOfLists) {
            System.out.println(eachRow);
        }

        System.out.println(queryResultAsListOfLists.get(1).get(2));

        String o = (String) (queryResultAsListOfLists.get(1).get(2));


        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);

        for (Map<String, Object> eachRow : queryResultListOfMaps) {
            System.out.println(eachRow);
        }

        System.out.println(queryResultListOfMaps.get(1).get("firstName"));

        System.out.println(DBUtility.getColumnNames(query));



        // Update

        DBUtility.updateQuery("UPDATE users SET email = 'duotech1999@gmail.com' WHERE username='duotech'");

        queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists(query);


        for (List<Object> eachRow : queryResultAsListOfLists) {
            System.out.println(eachRow);
        }
    }
}
