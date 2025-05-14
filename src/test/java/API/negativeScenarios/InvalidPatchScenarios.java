package API.negativeScenarios;

import API.BaseTest;
import API.PatchAPI;
import POJO.PatchRequestBody;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class InvalidPatchScenarios extends BaseTest {
    public InvalidPatchScenarios() throws Exception {
    }
    PatchAPI patchAPI = new PatchAPI();
    JsonPath responseJson;
    SoftAssert softAssert;
    Response response;

    @Test
    public void verifyResponseByPassingInvalidPatchFieldNameValue() {
        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName1", "new_Allianz_5998");
        response =patchAPI.patchAPIValidations(id,bearerToken,patchRequestBody,contentType);

        responseJson = new JsonPath(response.asString());
        softAssert = new SoftAssert();
        softAssert.assertEquals(responseJson.getString("errorMessage"), "Provided Patch Field is not supported"
        , "Expected message 'Provided Patch Field is not supported' but actual value is "+ responseJson.getString("errorMessage"));

        softAssert.assertAll();
    }

    @Test
    public void verifyResponseWithNoBody(){
        response =  patchAPI.patchAPIValidations(id,bearerToken,contentType);

        softAssert.assertEquals(response.statusCode(), 400,
                "Expected 400 status code but received " + response.getStatusCode());

        softAssert.assertAll();
    }

    @Test(dataProvider ="invalidAuthDataProvider")
    public void verifyInvalidAuth(String token){
        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName", "new_Allianz_5998");
        response =patchAPI.patchAPIValidations(id,token,patchRequestBody,contentType);

        softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 401,
                "Expected 401 status code but received " + response.getStatusCode());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[] invalidAuthDataProvider(){
        return new Object[]{
                "1245878889",
                ""
        };
    }

    @Test(dataProvider ="invalidAuthDataProvider")
    public void verifyInvalidId(String id){
        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName", "new_Allianz_5998");
        response =patchAPI.patchAPIValidations(id,bearerToken,patchRequestBody,contentType);

        softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 404,
                "Expected 404 status code but received " + response.getStatusCode());
        softAssert.assertAll();

    }

    @Test
    public void verifyResponseByNotPassingBody() {
        response =patchAPI.patchAPIValidations(id,bearerToken,contentType);

        softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400,
                "Expected status code 400 but received "+response.getStatusCode());

        softAssert.assertAll();
    }

    @Test
    public void verifyResponseByInvalidContentType(){
        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName", "new_Allianz_5998");
        response =patchAPI.patchAPIValidations(id,bearerToken,patchRequestBody);

        softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 415,
                "Exxpected 415 status code but received "+response.getStatusCode());
        softAssert.assertAll();
    }
}
