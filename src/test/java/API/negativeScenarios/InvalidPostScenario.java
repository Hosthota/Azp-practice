package API.negativeScenarios;

import API.BaseTest;
import API.PostAPI;
import POJO.PostRequestBody;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InvalidPostScenario extends BaseTest {
    public InvalidPostScenario() throws Exception {
    }
    PostAPI postApi = new PostAPI();
    SoftAssert softAssert ;

    @Test
    public void verifyResponseWhenEntryNameIsRepeated() {
        PostRequestBody postRequestBody = new PostRequestBody("Allianz_B142");
        Response response  = postApi.postAPIValidations(bearerToken,"application/json",postRequestBody);

        softAssert  = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Expected 200 status code but received "+ response.getStatusCode());

        Response secondResponse  = postApi.postAPIValidations(bearerToken,"application/json",postRequestBody);
        softAssert.assertEquals(secondResponse.getStatusCode(), 409,
        "Expected status code is 409, but received "+ secondResponse.getStatusCode());

        softAssert.assertEquals(secondResponse.asString(), "Object with provided name already exists",
        "Expected Message is 'Object with provided name already exists' but received "+ secondResponse.asString());

        softAssert.assertAll();
    }

   @Test(dataProvider = "InvalidAuth")
    public void verifyInvalidAuth(String auth){
        PostRequestBody postRequestBody = new PostRequestBody("Allianz_B135");
        Response response  = postApi.postAPIValidations(auth,"application/json", postRequestBody);

       softAssert  = new SoftAssert();
       softAssert.assertEquals(response.getStatusCode(), 401,
                "Expected status code 401 but Actual status code is "+ response.getStatusCode());
       softAssert.assertAll();
    }

    @DataProvider(name ="InvalidAuth")
    public Object[] invalidAuthDataProvider(){
        return new Object[]{
                "12345",
                ""
        };
    }

    @Test
    public void verifyResponseWhenBodyIsNotSent() {
        Response response = postApi.postAPIValidations(bearerToken, "application/json");

        softAssert  = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400,
                "Expected 400 status code but received " + response.getStatusCode());

        softAssert.assertAll();

    }

    @Test
    public void verifyResponseWhenInvalidContentTypeIsSent() {
        PostRequestBody postRequestBody = new PostRequestBody("Allianz_B135");
        Response response = postApi.postAPIValidations(bearerToken , postRequestBody);

        softAssert  = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 415,
                "Expected status code 415 but received "+response.getStatusCode());

        softAssert.assertAll();
    }
}
