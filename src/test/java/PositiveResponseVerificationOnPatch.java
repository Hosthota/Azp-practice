import API.PostAPIValidations;
import POJO.POST_API;
import POJO.PatchRequestBody;
import POJO.PatchResponseBody;
import POJO.Post_Response;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PositiveResponseVerificationOnPatch extends BaseTest{


    public PositiveResponseVerificationOnPatch() throws Exception {
    }

    @BeforeClass
    public void setBasicUri() {
        RestAssured.baseURI = "http://localhost:9000";
    }


    @Test
    public void verifyPositiveResponseWithValidDetails() {
        PatchRequestBody patchRequestBody = new PatchRequestBody("entryName","new_Allianz_5997");
        PatchResponseBody patchResponseBody = given()
                .header("Authorization", bearerToken)
                .contentType("application/json")
                .pathParam("id", id)
                .body(patchRequestBody)
                .patch("/object")
                .then()
                .statusCode(200)
                .extract().as(PatchResponseBody.class);





    }
}
