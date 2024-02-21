package qtriptest.pages;

import qtriptest.SeleniumWrapper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends SeleniumWrapper{
    @FindBy(css="input[name=email]")
    WebElement email_inputBox;
    
    @FindBy(css="input[name=password]")
    WebElement password_inputBox;

    @FindBy(xpath="//button[contains(text(),'Login')]")
    WebElement login_Button;

    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver,Duration.ofSeconds(15));
    }


    public void login_user(String email, String password){
        wait.until(ExpectedConditions.urlContains("login"));
        custom_sendKeys(email,email_inputBox);
        custom_sendKeys(password,password_inputBox);
        custom_click(driver,login_Button);
    }

    public Boolean isRegisterationSuccessful(){
        wait.until(ExpectedConditions.visibilityOf(login_Button));
        return driver.getCurrentUrl().contains("login");
    }

    public Boolean isLoginSuccessful(){
        wait.until(ExpectedConditions.urlToBe(HomePage.homePage_URL));
        return !driver.getCurrentUrl().contains("login");
    }

}
