package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.RegisterPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_03 extends BaseTest{

    @Test(description="TestCase3",priority=3,groups = {"Booking and Cancellation Flow"},enabled=true,dataProvider = "dataProviderMethod",dataProviderClass = DP.class)
    public void UI_TestCase03(String userName,String password,String city,String adventure,String name,String date,String count) throws InterruptedException{
        //Goto HomePage
        SoftAssert softassert=new SoftAssert();
        homePage.navigateToHomePage();

        //Create new user
        homePage.clickOnRegister();
        registerPage.registerDynamicUser(userName, password);

        //Login user
        softassert.assertTrue(loginPage.isRegisterationSuccessful(),"Registeration Failed");
        loginPage.login_user(RegisterPage.userEmail, password);

        //search for adventure
        homePage.searchForCity(city);
        homePage.verifySearchResults(city,true);
        adventurePage.clearAllFilters();
        adventurePage.searchForAdventure(adventure);
        adventurePage.selectAdventure(adventure);
        
        //Fill details
        softassert.assertTrue(adventureDetailsPage.ifLandedOnAdventureDetailsPage());
        adventureDetailsPage.setName(name);
        adventureDetailsPage.setDate(date);
        //System.out.println("*********************************"+date+"*****************************************");
        Thread.sleep(3000);
        adventureDetailsPage.setCount(count);
        adventureDetailsPage.clickReserve();
        softassert.assertTrue(adventureDetailsPage.isAdventureReserved());

        //check Booking History
        adventureDetailsPage.checkReservedBooking();
        //Thread.sleep(5000);
        //String id=historyPage.getTransactionID();
        
        //Cancel the reservation
        historyPage.cancelReservation();

        //Refresh the page and check if reservation was cancelled
        driver.navigate().refresh();
        softassert.assertTrue(historyPage.ifReservationExists()==0);
        softassert.assertAll();
        historyPage.clickOnLogout();
        
        

    }
    
}
