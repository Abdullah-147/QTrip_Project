package qtriptest.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qtriptest.DP;
import qtriptest.pages.RegisterPage;

public class testCase_01 extends BaseTest{

    @Test(description = "TestCase1",groups = "Login Flow",dataProvider = "dataProviderMethod",dataProviderClass = DP.class,enabled=true,priority=1)
    public void UI_TestCase01(String userName,String password) {
        SoftAssert softassert=new SoftAssert();
        

        //Go to HomePage
        homePage.navigateToHomePage();
        softassert.assertTrue(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/"),"Landed on incorrect URL: "+driver.getCurrentUrl());
        homePage.clickOnRegister();

        //Register new user 
        registerPage.registerDynamicUser(userName,password); 
        
        //Login with registered user
        softassert.assertTrue(loginPage.isRegisterationSuccessful(),"Registeration Failed");
        loginPage.login_user(RegisterPage.userEmail, password);
        softassert.assertTrue(loginPage.isLoginSuccessful(),"Login Failed");
       
        //Logout
        homePage.clickOnLogout();
        softassert.assertTrue(homePage.isLoginButtonDisplayed(),"Unable to Logout");
        softassert.assertAll();
    }

    
}
