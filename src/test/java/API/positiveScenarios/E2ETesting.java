package API.positiveScenarios;

import API.BaseTest;
import API.GetAPI;
import API.PatchAPI;
import API.PostAPI;
import POJO.*;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class E2ETesting extends BaseTest {
    public E2ETesting() {
    }

    PostAPI postApi = new PostAPI();
    GetAPI getAPI = new GetAPI();
    PatchAPI patchAPI = new PatchAPI();
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Verify end to end testing by performing POST, GET and PATCH API")
    public void verifyEndToEndTesting() {
        PostRequestBody postRequestBody = new PostRequestBody("Allianz_c129");

        Response postResponse =  postApi.postAPIValidations(bearerToken,contentType,postRequestBody);

        softAssert.assertEquals(postResponse.getStatusCode(), 200,
                "Post API : Expected status code 200 but received "+ postResponse.getStatusCode());
        log.info("Got Post API responses successfully");

        PostResponseBody postResponseBody = postResponse.as(PostResponseBody.class);
        String id = postResponseBody.getId();

        Response getResponse =getAPI.getAPIValidations(id, bearerToken);
        softAssert.assertEquals(getResponse.getStatusCode(), 200,
                "Get API : Expected status code 200 but received "+ postResponse.getStatusCode());
        log.info("Got GET API responses successfully");

        GetResponseBody getResponseBody = getResponse.as(GetResponseBody.class);
        softAssert.assertEquals(postRequestBody.getEntryName(),getResponseBody.getEntryName(),
                "Expected entryName value is "+postRequestBody.getEntryName() +" but received "+getResponseBody.getEntryName());
        log.info("Sent entryName in POST requestBody matches with received entryName in GET response body");

        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName", "new_Allianz_a5998");
        Response patchResponse = patchAPI.patchAPIValidations(id,bearerToken,patchRequestBody,contentType);

        softAssert.assertEquals(patchResponse.getStatusCode(), 200,
                "Patch API: Expected 200 but received "+ patchResponse.getStatusCode());
        PatchResponseBody patchResponseBody = patchResponse.as(PatchResponseBody.class);
        log.info("Updated entryName using PATCH API successfully");

        Response getResponseAfterPatch = getAPI.getAPIValidations(id,bearerToken);
        softAssert.assertEquals(getResponseAfterPatch.getStatusCode(), 200,
                "Get API : Expected status code 200 but received "+ postResponse.getStatusCode());

        GetResponseBody getResponseBodyAfterPatch = getResponseAfterPatch.as(GetResponseBody.class);
        softAssert.assertEquals(patchResponseBody.getEntryName(),getResponseBodyAfterPatch.getEntryName(),
                "Expected entryName value is "+patchResponseBody.getEntryName() +" but received "+getResponseBodyAfterPatch.getEntryName());
        log.info("Sent entryName in PATCH requestBody matches with received entryName in GET response body");

        softAssert.assertAll();
        log.info("E2E testing completed");
    }

}
