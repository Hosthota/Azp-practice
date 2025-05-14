package API.positiveScenarios;

import API.BaseTest;
import API.GetAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonSchemaCompare;

public class PositiveResponseVerificationOnGet extends BaseTest {

    public PositiveResponseVerificationOnGet() throws Exception {
    }
    GetAPI getAPI = new GetAPI();
    JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();

    @Test
    public void verifyPositiveResponseWithValidDetails() throws Exception{
        Response response = getAPI.getAPIValidations(id, bearerToken);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200,
                "Expected status code 200 but received "+ response.statusCode());

        jsonSchemaCompare.compareResponseWithJsonSchema(response, "PositiveGetResponse.json");

        softAssert.assertAll();

    }
}
