import API.GetAPIValidations;
import API.PostAPIValidations;
import POJO.POST_API;
import POJO.Post_Response;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PositiveResponseVerificationOnPost extends BaseTest {
    public PositiveResponseVerificationOnPost() throws Exception {
    }

    @BeforeClass
    public void setBasicUri() {
        RestAssured.baseURI = "http://localhost:9000";
    }


    @Test
    public void verifyPositiveResponseWithValidDetails() {
        POST_API po = new POST_API("Allianz_a128");
        Post_Response postResponsee = given()
                .header("Authorization", bearerToken)
                .contentType("application/json")
                .body(po)
                .post("/object")
                .then()
                .statusCode(200)
                .extract().as(Post_Response.class);

            PostAPIValidations postApiValidations = new PostAPIValidations();

            postApiValidations.verifyRequestBody(postResponsee, po);

            }
}
