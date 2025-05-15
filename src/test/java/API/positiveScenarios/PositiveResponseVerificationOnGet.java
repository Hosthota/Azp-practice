package API.positiveScenarios;

import API.BaseTest;
import API.GetAPI;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonSchemaCompare;

public class PositiveResponseVerificationOnGet extends BaseTest {

    public PositiveResponseVerificationOnGet() {

    }
    GetAPI getAPI = new GetAPI();
    JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();

    @Test(description = "Verify response by passing all the valid data")
    public void verifyPositiveResponseWithValidDetails(){
        Response response = getAPI.getAPIValidations(id, bearerToken);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200,
                "Expected status code 200 but received "+ response.statusCode());

        jsonSchemaCompare.compareResponseWithJsonSchema(response, "PositiveGetResponse.json");

        softAssert.assertAll();
        log.info("Completed with Get Request");

    }
}
