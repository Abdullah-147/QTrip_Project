package qtriptest.APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify that a new user can be registered and login using APIs of QTrip
public class testCase_API_01 {
    String URI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
    String basePath_Register= "api/v1/register";
    String basePath_Login= "api/v1/login";
    String email="abdul"+UUID.randomUUID()+"@gmail.com";
    String password="Abdul@0000";
    static String token;
    static String user_Id;

    @Test
    public void testcase01a_ValidRegisteration(){
        testCase_API_01 t1=new testCase_API_01();
        t1.RegisterWithValidCreds();
    }

    @Test
    public void testCase01b_InvalidRegisteration(){
        testCase_API_01 t1=new testCase_API_01();
        t1.RegisterWithInvalidCreds();
    }

    @Test(description="Verify that a new user can be registered and login using APIs of QTrip",groups={"API Tests"})
    public void API_testCase01c_ValidLogin(){
        testCase_API_01 t1=new testCase_API_01();
        t1.RegisterWithValidCreds();
        t1.LoginWithValidCreds();
    }

    @Test
    public void testCase01d_InvalidLogin(){
        testCase_API_01 t1=new testCase_API_01();
        t1.RegisterWithValidCreds();
        t1.LoginWithInvalidCreds();
    }

    public void RegisterWithValidCreds(){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_Register;
        JSONObject obj=new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        obj.put("confirmpassword", password);
        Response response=RestAssured.given().body(obj.toString()).contentType("application/json").when().post();
        //response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201,"Registration failed.");
        Assert.assertTrue(response.jsonPath().getBoolean("success"),"Registration failed.");
    }

    public void RegisterWithInvalidCreds(){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_Register;
        JSONObject obj=new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        obj.put("confirmpassword", "password");
        Response response=RestAssured.given().body(obj.toString()).contentType("application/json").when().post();
        //response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 400,"Registration success with password and confrim password not same..");
        Assert.assertFalse(response.jsonPath().getBoolean("success"),"Registration success with password and confrim password not same..");
        Assert.assertEquals(response.jsonPath().getString("message"), "Password & confirm password doesn't match");
    }

    public void LoginWithValidCreds(){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_Login;
        JSONObject obj=new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        Response response=RestAssured.given().body(obj.toString()).contentType("application/json").when().post();
        //response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201,"Login Failed..");
        Assert.assertTrue(response.jsonPath().getBoolean("success"),"Login Failed..");
        token=response.jsonPath().getString("data.token");
        user_Id=response.jsonPath().getString("data.id");
        Assert.assertNotNull(token);
        Assert.assertNotNull(user_Id);
    }

    public void LoginWithInvalidCreds(){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_Login;
        JSONObject obj=new JSONObject();
        obj.put("email", email);
        obj.put("password", "password");
        Response response=RestAssured.given().body(obj.toString()).contentType("application/json").when().post();
        //response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 403,"Logged in with Invalid Creds..");
        Assert.assertEquals(response.jsonPath().getString("message"), "Password is incorrect");
    }

    
}
