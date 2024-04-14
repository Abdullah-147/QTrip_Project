package qtriptest.APITests;

import java.util.UUID;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//Verify that a duplicate user account cannot be created on the Qtrip Website
public class testCase_API_04 {
    //1.register a new user
    //2. Again register a new user with the same email Id    

    String URI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
    String basePath_Register= "api/v1/register";
    String email="abdul"+UUID.randomUUID()+"@gmail.com";
    String password="Abdul@0000";

    @Test(description="Verify that a duplicate user account cannot be created on the Qtrip Website",groups={"API Tests"})
    public void API_testCase04_checkDuplicateRegisteration(){
        String email="testuser"+UUID.randomUUID()+"@gmail.com";

        //Register a new user
        registeration(email,201);

        //Duplicate registeration
        registeration(email,400);
    }

    public void registeration(String email,int expectedStatusCode){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_Register;
        JSONObject obj=new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        obj.put("confirmpassword", password);
        Response response=RestAssured.given().body(obj.toString()).contentType("application/json").when().post();
        //response.then().log().all();
        if(expectedStatusCode==201) 
        {
            Assert.assertEquals(response.getStatusCode(), expectedStatusCode,"Registration failed.");
            Assert.assertTrue(response.jsonPath().getBoolean("success"));
        }
        else if(expectedStatusCode==400){
            Assert.assertEquals(response.getStatusCode(), expectedStatusCode,"Duplicate registeration done..");
            Assert.assertFalse(response.jsonPath().getBoolean("success"));
            Assert.assertEquals(response.jsonPath().getString("message"), "Email already exists");
        }

    }

    
}


  

