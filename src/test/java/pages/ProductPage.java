package pages;

import com.fasterxml.jackson.core.JsonParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductPage {

    @FindBy(xpath = "//h1")
    public WebElement productTitle;
    @FindBy(id = "our_price_display")
    public WebElement productPrice;




    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
