package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.pageObject;

import java.util.concurrent.TimeUnit;

public class AmazonWeb{

    WebDriver driver;
    pageObject PageObject;

    @BeforeClass
    public void browserSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        PageObject = new pageObject(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String browseUrl="https://www.amazon.com/";
        driver.navigate().to(browseUrl);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        PageObject.removeCart(driver);
        driver.quit();
    }

    @Test(priority = 1)
    @Description("Add product into the cart and change the quantity of the product")
    public void test() throws Exception{
        PageObject.searchInput(driver);
        PageObject.AddCart(driver);
        PageObject.changeProductQnt(driver);

    }

}
