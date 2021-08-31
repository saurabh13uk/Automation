package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.Utilies;

import java.util.List;

public class pageObject{

    Utilies utilies = new Utilies();

    public pageObject(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement eleSearchField;

    @FindBy(id="nav-search-submit-button")
    WebElement eleSearchBtn;

    @FindBys(@FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']"))
    List<WebElement> searchResults;

    @FindBy(id = "add-to-cart-button")
    public WebElement addCartBtn;

    @FindBy(id = "nav-cart-count")
    public WebElement cartMenu;

    @FindBy(xpath = "//span[@class='a-truncate-cut']")
    public WebElement cartProduct;

    @FindBy(id = "sc-subtotal-label-activecart")
    public WebElement subTotalText;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']/span")
    public WebElement subTotalAmount;

    @FindBy(name = "quantity")
    public WebElement qtnDropdown;

    @FindBy(name = "quantityBox")
    public WebElement quantityBox;

    @FindBy(id = "a-autoid-1-announce")
    public WebElement updateBtn;

    @FindBy(xpath = "//input[@value='Delete']")
    public WebElement deleteBtn;

    @FindBy(xpath = "//h1[@class='a-spacing-mini a-spacing-top-base']")
    public WebElement emptyCart;

    public void searchInput(WebDriver driver){
        utilies.clickElement(driver,eleSearchField);
        eleSearchField.sendKeys("SAMSUNG Galaxy S21");
        utilies.clickElement(driver,eleSearchBtn);
    }

    public float captureProductPrice(){
        String price = subTotalAmount.getText().trim();
        float amount = Float.parseFloat(price.substring(1));
        System.out.println(amount);
        return amount;
    }

    public void changeProductQnt(WebDriver driver) throws InterruptedException {
        float addedProductAmount = captureProductPrice();
        Select selectQnt = new Select(qtnDropdown);
        selectQnt.selectByVisibleText("10+");
        //utilies.waitForElementToBeClickable(driver,quantityBox);
        Thread.sleep(2000);
        quantityBox.clear();
        utilies.clickElement(driver,quantityBox);
        quantityBox.sendKeys("0");
        utilies.clickElement(driver,updateBtn);
        float currentPrice = captureProductPrice();
        Assert.assertTrue(currentPrice==(addedProductAmount*10));
    }

    public void AddCart(WebDriver driver) throws InterruptedException {
        String product = searchResults.get(0).getText();
        WebElement element = driver.findElement(By.xpath("//span[text()='"+product+"']"));
        utilies.clickElement(driver,element);
        utilies.clickElement(driver,addCartBtn);
        Thread.sleep(2000);
        utilies.clickElement(driver,cartMenu);
        //Assert.assertEquals(cartProduct.getText(),product,"Product mismatch");
        Assert.assertEquals(subTotalText.getText().trim(),"Subtotal (1 item):");
    }

    public void removeCart(WebDriver driver){
        utilies.clickElement(driver,cartMenu);
        utilies.clickElement(driver,deleteBtn);
        Assert.assertEquals(emptyCart.getText().trim(),"Your Amazon Cart is empty.");
    }


}
