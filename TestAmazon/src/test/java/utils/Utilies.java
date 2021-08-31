package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilies {
    public final int TimeOut = 30;

    public void clickElement(WebDriver driver ,WebElement element) {
        waitForElementToBeClickable(driver, element);
        element.click();
    }

    public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,TimeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
