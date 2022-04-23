package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.io.File;
import java.time.Duration;

public class Hooks {



    @Before
    public void setup(){

        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().manage().deleteAllCookies();
        //



    }

//    @Before ("@module2")   // the before logic that runs before all scenarios tagged with @module2
//    public void setup2(){
//
//        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Driver.getDriver().manage().window().maximize();
////        Driver.getDriver().manage().deleteAllCookies();
//
//
//
//    }

    @After
    public void tearDown(Scenario scenario){

        if(scenario.isFailed()){
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshotAs = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotAs, "image/png", "failed");
        }

        Driver.quitDriver();
    }


}
