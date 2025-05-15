package API.positiveScenarios;

import API.BaseTest;
import API.PatchAPI;
import POJO.PatchRequestBody;
import POJO.PatchResponseBody;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonSchemaCompare;


public class PositiveResponseVerificationOnPatch extends BaseTest {


    public PositiveResponseVerificationOnPatch() throws Exception {}

    PatchAPI patchAPI = new PatchAPI();
    JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();
    SoftAssert softAssert;

    @Test
    public void verifyPositiveResponseWithValidDetailsByUpdatingEntryName() throws  Exception{
        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName", "new_Allianz_5899");
        Response response = patchAPI.patchAPIValidations(id,bearerToken,patchRequestBody,contentType);
        softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200,
        "Expected status code 200 but received "+response.getStatusCode());

        jsonSchemaCompare.compareResponseWithJsonSchema(response,"PositivePatchResponse.json");

        PatchResponseBody patchResponseBody = response.as(PatchResponseBody.class);
        patchAPI.verifyRequestBody(patchRequestBody, patchResponseBody);

        softAssert.assertAll();
    }

    @Test
    public void verifyPositiveResponseWithValidDetailsByUpdatingEndTime() throws Exception {
        PatchRequestBody patchRequestBody = new PatchRequestBody("endTime", "2025-05-19");
        Response response = patchAPI.patchAPIValidations(id,bearerToken,patchRequestBody,contentType);
        softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but received "+response.getStatusCode());

        jsonSchemaCompare.compareResponseWithJsonSchema(response,"PositivePatchResponse.json");

        PatchResponseBody patchResponseBody = response.as(PatchResponseBody.class);
        patchAPI.verifyRequestBody(patchRequestBody, patchResponseBody);

        softAssert.assertAll();
    }
}
