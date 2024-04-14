package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

//Verify that a reservation can be made using the QTrip API
//Flow will be-> Register->Login->Book reservation

public class testCase_API_03 {
    String URI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
    String basePath_bookReservation= "api/v1/reservations/new";
    String basePath_checkReservation="api/v1/reservations";

    @Test(description="Verify that a reservation can be made using the QTrip API",groups={"API Tests"})
    public void testCase03_BookReservation(){
        testCase_API_01 t1=new testCase_API_01();
        t1.testCase01c_ValidLogin();
        System.out.println(testCase_API_01.token+"\n"+testCase_API_01.user_Id);
        bookReservation(testCase_API_01.token,testCase_API_01.user_Id,"1773524915");
        checkReservation(testCase_API_01.token,testCase_API_01.user_Id,"1773524915");
    }

    public void bookReservation(String token,String id, String adventureId){
        
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_bookReservation;


        JSONObject obj=new JSONObject();
        obj.put("userId", id);
        obj.put("name", "testuser");
        obj.put("date", "2024-09-09");
        obj.put("person", "1");
        obj.put("adventure", adventureId);

        String authToken="Bearer "+token;

        Response response=RestAssured.given().header("Authorization",authToken).contentType("application/json").body(obj.toString()).log().all().when().post();
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));

    }

    public void checkReservation(String token,String id, String adventureId){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_checkReservation;

        String authToken="Bearer "+token;
        Response response=RestAssured.given().queryParam("id", id).header("Authorization",authToken).contentType("application/json").log().all().when().get();

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].adventure"), adventureId);

    }
}
