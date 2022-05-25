package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        tags = "@trigger",    // will run all scenarios with the given tag
//        @search or @productDetails -> all scenarios tagged with either @search or @productDetails

        //@search and @product -> all scenarios tagged with both @search and @product

       // "not @product" -> all scenarios that are NOT tagged with @product

        features = "src/test/resources", // path to a folder where feature files are located
                                        // //Cucumber will use the package of the runner class if this option is not provided
        glue = "stepDefinitions",        // path to the folder where step definition classes are located
                                     //Cucumber will use the package of the runner class if this option is not provided

        stepNotifications = true,     //shows each step of the scenario in the report

//        snippets = CucumberOptions.SnippetType.CAMELCASE,

        plugin = {"pretty",  // prints out more info on the consoile
                  "html:target/built-in-report/built-in-report.html", // generates built in html report
                  "json:target/cucumber.json", // generates a built in json report which is needed for pretty report
                  "rerun:target/failed.txt"  // generates a text file with the list of failed scenarios
        }


//        ,dryRun = true             // step definition execution is skipped, used for generating snippets without running the code
)


public class CucumberRunner {
}
