package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/resources", // path to a folder where feature files are located
        glue = "stepDefinitions"

)


public class CucumberRunner {
}
