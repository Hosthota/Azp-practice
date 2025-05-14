package API.negativeScenarios;

import API.BaseTest;
import API.GetAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class InvalidGETScenarios extends BaseTest {
    public InvalidGETScenarios() throws Exception {
    }

    JsonPath responseJson;
    GetAPI getAPI = new GetAPI();
    SoftAssert softAssert;

    @Test(dataProvider ="InvalidId")
    public void verifyIdNegativeScenario(String id){
        Response response = getAPI.getAPIValidations(id, bearerToken);
        softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 404,
                "Expected status code is 404 for invalid ID, received status code is "+ response.statusCode());

        responseJson = new JsonPath(response.asString());
        softAssert.assertEquals(responseJson.getString("errorMessage"), "Object with provided ID was not found",
                "Expected error message is 'Object with provided ID was not found' but received "+ responseJson.get("errorMessage"));

        softAssert.assertAll();
    }

    @DataProvider(name = "InvalidId")
    public Object[] invalidIdDataProvider(){
            return new Object[]{
                "123455879598",
                    ""
            };
    }


@Test(dataProvider = "InvalidAuth")
    public void verifyAuthNegativeScenario(String auth){
        Response response = getAPI.getAPIValidations(id, auth);
    softAssert = new SoftAssert();
    softAssert.assertEquals(response.statusCode(), 401,
                "Expected 401 status code with invalid auth, but received "+response.statusCode());
        responseJson = new JsonPath(response.asString());
    softAssert.assertEquals(responseJson.getString("errorMessage"), "Invalid or missing header.",
                "Expected error message is 'Invalid or missing header.' but received "+ responseJson.get("errorMessage"));
    softAssert.assertAll();
    }

    @DataProvider(name = "InvalidAuth")
    public Object[] invalidAuthDataProvider(){
        return new Object[]{
                "12345",
                ""
        };
    }


    @Test
    public void verifyWithoutPassingQueryParam(){
        Response response = getAPI.getAPIValidations(bearerToken);
        softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400,
                "Expected status code 400 but received "+response.statusCode());
        softAssert.assertAll();
    }
}
