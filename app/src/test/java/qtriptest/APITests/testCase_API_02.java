package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.io.File;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify that the search City API Returns the correct number of results
public class testCase_API_02 {
    String URI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
    String basePath_searchCity= "api/v1/cities";

    @Test(description="Verify that the search City API Returns the correct number of results",groups={"API Tests"})
    public void testCase02_searchCities(){
        testCase_API_02 t2=new testCase_API_02();
        t2.SearchCity();
    }

    public void SearchCity(){
        RestAssured.baseURI=URI;
        RestAssured.basePath=basePath_searchCity;

        Response response=RestAssured.given().queryParam("q", "beng").when().get();
        response.then().log().all();
        File file=new File("/home/crio-user/workspace/abdullahjamadar01-ME_API_TESTING_PROJECT/app/src/test/resources/schema.json");
        JsonSchemaValidator js=JsonSchemaValidator.matchesJsonSchema(file);
        
        //Validate the json Schema
        response.then().assertThat().body(js);

        //Validate the response body length
        response.then().assertThat().body("size()",Matchers.equalTo(1));

        //Validate status code
        Assert.assertEquals(response.getStatusCode(), 200,"Unable to search for City..");

        //Validate the "description"
        Assert.assertEquals(response.jsonPath().getString("[0].description"), "100+ Places","Received invalid description of city");
        
    }







}
