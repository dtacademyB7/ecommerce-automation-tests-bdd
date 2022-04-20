package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Homepage {


    public Homepage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (id = "search_query_top")
    public WebElement searchBar;



}
