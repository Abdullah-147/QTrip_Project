package qtriptest.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;

public class BaseTest {
    public static WebDriver driver;
    static HomePage homePage;
    static RegisterPage registerPage;
    static LoginPage loginPage;
    static AdventurePage adventurePage;
    static AdventureDetailsPage adventureDetailsPage;
    static HistoryPage historyPage;
    

    @BeforeSuite(alwaysRun=true)
    public void driverSetup() throws IOException, MalformedURLException{
        System.out.println("Suite started. Initializing driver...");
        BaseTest.driver=SingletonDriver.createDriverInstance();
        homePage=new HomePage(driver);
        registerPage=new RegisterPage(driver);
        loginPage=new LoginPage(driver);
        adventurePage=new AdventurePage(driver);
        adventureDetailsPage=new AdventureDetailsPage(driver);
        historyPage=new HistoryPage(driver);
        try{FileUtils.forceDelete(new File("./ScreenCaptures"));}catch(Exception e) {};
        File screenshotFolder=new File("./ScreenCaptures");
        if(!screenshotFolder.exists()){
            screenshotFolder.mkdir();
        }
    }

    @AfterSuite(alwaysRun=true)
    public void tearDown(){
        driver.quit();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    
}
