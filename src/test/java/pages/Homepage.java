package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;
import utilities.Driver;

import java.util.List;

public class Homepage {


    @FindBy (xpath = "(//a[@title='Faded Short Sleeve T-shirts'])[2]")
    public WebElement product1;

    @FindBy (xpath = "(//a[@title='Blouse'])[2]")
    public WebElement product2;


    public void clickOnAProduct(String name){
        String xpath = "(//a[@title='"+name+"'])[2]";
        Driver.getDriver().findElement(By.xpath(xpath)).click();
    }

    public Homepage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (id = "search_query_top")
    public WebElement searchBar;

    @FindBy (xpath = "//ul[@id='homefeatured']//a[@class='product-name']")
    public List<WebElement> products;





}
