package API.positiveScenarios;

import API.BaseTest;
import API.PostAPI;
import POJO.PostRequestBody;
import POJO.PostResponseBody;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonSchemaCompare;

public class PositiveResponseVerificationOnPost extends BaseTest {

    public PositiveResponseVerificationOnPost(){}

    PostAPI postApi = new PostAPI();
    JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();


    @Test(description = "Verify response by passing valid details")
    public void verifyPositiveResponseWithValidDetails(){
        PostRequestBody postRequestBody = new PostRequestBody("Allianz_a131");
        Response response =  postApi.postAPIValidations(bearerToken,contentType,postRequestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200,
                "Expected status code 200 but received "+response.statusCode());

        jsonSchemaCompare.compareResponseWithJsonSchema(response,"PositivePostResponse.json");

        PostResponseBody postResponseBody = response.as(PostResponseBody.class);
        postApi.verifyRequestBody(postResponseBody, postRequestBody);

        softAssert.assertAll();
    }
}
