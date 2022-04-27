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

    @FindBy(id = "quantity_wanted")
    public WebElement quantity;


    @FindBy(xpath = "//i[@class='icon-plus']")
    public WebElement plusButton;

    @FindBy(xpath = "//span[@itemprop='sku']")
    public WebElement productModel;

    @FindBy(xpath = "//table[@class='table-data-sheet']//tr[1]//td[2]")
    public WebElement productComposition;
    @FindBy(xpath = "//table[@class='table-data-sheet']//tr[2]//td[2]")
    public WebElement productStyle;
    @FindBy(xpath = "//table[@class='table-data-sheet']//tr[3]//td[2]")
    public WebElement productProperty;


    public void clickOnPlusButton(int times){

        for (int i = 0; i < times; i++) {
            plusButton.click();
        }
    }


    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
