package qtriptest.pages;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qtriptest.SeleniumWrapper;

public class HomePage extends SeleniumWrapper{

    @FindBy(xpath="//*[text()='Register']")
    WebElement registerButton;

    @FindBy(xpath="//*[text()='Login Here']")
    WebElement loginButton;

    @FindBy(xpath="//*[text()='Logout']")
    WebElement logoutButton;

    @FindBy(css="#autocomplete")
    WebElement searchBox_City;

    @FindBy(css="ul[id=results]")
    WebElement searchResults;
    
    WebDriver driver;
    public static String homePage_URL="https://qtripdynamic-qa-frontend.vercel.app/";
    WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        
    }

    public void navigateToHomePage(){
        custom_get(driver, homePage_URL);
    }

    public void clickOnRegister(){
        custom_click(driver,wait.until(ExpectedConditions.elementToBeClickable(registerButton)));
    }

    public void clickOnLogin(){
        custom_click(driver,wait.until(ExpectedConditions.elementToBeClickable(loginButton)));
    }

    public void clickOnLogout(){
        custom_click(driver,wait.until(ExpectedConditions.elementToBeClickable(logoutButton)));
    }

    public Boolean isLoginButtonDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
    }

    public void searchForCity(String city){
        custom_sendKeys(city,searchBox_City);
    }

    public Boolean verifySearchResults(String city,Boolean isValidCity){
    	wait=new WebDriverWait(driver,Duration.ofSeconds(5));
    	try {
        if(isValidCity) {
        	Boolean b=wait.until(ExpectedConditions.textToBePresentInElement(searchResults,city));
        	custom_click(driver,searchResults);
        	return b;
        }
        else
        	return wait.until(ExpectedConditions.textToBePresentInElement(searchResults,"No City found"));
    	}catch(Exception e) {
    		searchForCity(city);
    		if(isValidCity) {
            	Boolean b=wait.until(ExpectedConditions.textToBePresentInElement(searchResults,city));
            	custom_click(driver,searchResults);
            	return b;
            }
            else
            	return wait.until(ExpectedConditions.textToBePresentInElement(searchResults,"No City found"));
        	}
    	}
    }

