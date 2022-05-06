package pages.duotifyPages;

import com.fasterxml.jackson.databind.DatabindContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;
import utilities.Driver;

public class HomePageDuo {



    @FindBy(xpath = "//button[.='USER DETAILS']")
    public WebElement userDetails;

    @FindBy(name = "email")
    public WebElement emailInput;

    public HomePageDuo(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "nameFirstAndLast")
    public WebElement nameFirstAndLast;
}
