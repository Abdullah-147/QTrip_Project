package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.RegisterPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_04 extends BaseTest
{
    int expectedReservationCount;
    @Test(description="TestCase4",priority=4,enabled=true,groups = "Reliability Flow",dataProvider = "dataProviderMethod",dataProviderClass = DP.class)
    public void TestCase04(String userName,String password,String adventureDetails1,String adventureDetails2,String adventureDetails3) throws InterruptedException{
        expectedReservationCount=0;
        //Goto Qtrip homePage
        SoftAssert softassert=new SoftAssert();
        homePage.navigateToHomePage();
        
        //Register new user
        homePage.clickOnRegister();
        registerPage.registerDynamicUser(userName, password);
        softassert.assertTrue(loginPage.isRegisterationSuccessful(),"Registeration Failed");

        //Login user
        loginPage.login_user(RegisterPage.userEmail, password);
        softassert.assertTrue(loginPage.isLoginSuccessful(),"Login Failed");

        //Reserve for an adventure
        reserveBooking(adventureDetails1);
        reserveBooking(adventureDetails2);
        reserveBooking(adventureDetails3);

        //Check if all reservations Exists
        adventureDetailsPage.checkReservedBooking();
        int reservationCount=historyPage.ifReservationExists();
        softassert.assertTrue(reservationCount==expectedReservationCount);
        homePage.clickOnLogout();
        softassert.assertAll();

    }

    public void reserveBooking(String adventures) throws InterruptedException{

        expectedReservationCount++;
        homePage.navigateToHomePage();
        SoftAssert softassert=new SoftAssert();
        String [] adventuredetails=adventures.split(";");

        //Search for an adventure
        homePage.searchForCity(adventuredetails[0]);
        homePage.verifySearchResults(adventuredetails[0],true);
        adventurePage.clearAllFilters();
        adventurePage.searchForAdventure(adventuredetails[1]);
        adventurePage.selectAdventure(adventuredetails[1]);

        //Book an adventure
        softassert.assertTrue(adventureDetailsPage.ifLandedOnAdventureDetailsPage());
        adventureDetailsPage.setName(adventuredetails[2]);
        adventureDetailsPage.setDate(adventuredetails[3]);
        adventureDetailsPage.setCount(adventuredetails[4]);
        adventureDetailsPage.clickReserve();
        softassert.assertTrue(adventureDetailsPage.isAdventureReserved());
    }
}
