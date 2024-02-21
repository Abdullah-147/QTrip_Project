package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {
    public static Boolean custom_click(WebDriver driver,WebElement e){
        Boolean b=e.isDisplayed();
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", e);
        
        e.click();

        return b;
    }

    public static void custom_sendKeys(String text,WebElement e){
        e.clear();
        e.sendKeys(text);
    }

    public static WebElement findElementWithRetry(WebDriver driver,By locator, int retryCount){

        int i=1;
        if(!driver.findElement(locator).isDisplayed())
        {
        while(i<=retryCount){
            driver.findElement(locator);
        }
        }
        return driver.findElement(locator);
    }

    public static void custom_get(WebDriver driver,String url){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }
    }
}
