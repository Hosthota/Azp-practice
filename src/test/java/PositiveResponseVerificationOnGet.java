import API.GetAPIValidations;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class PositiveResponseVerificationOnGet extends BaseTest {

    public PositiveResponseVerificationOnGet() throws Exception {
    }
    GetAPIValidations getAPIValidations = new GetAPIValidations();

    @Test
    public void verifyPositiveResponseWithValidDetails(){
    RestAssured.baseURI = "http://localhost:9000";
        Response response = given()
                .queryParam("id", id)
                .header("Authorization", bearerToken)
                .when()
                .get("/object")
                .then()
                .extract().response();

        getAPIValidations.verifyStatusCode(response);
        getAPIValidations.verifyRequestBody(response);
    }
}
