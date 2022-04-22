package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.Driver;

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
    public void tearDown(){

        Driver.quitDriver();
    }


}
