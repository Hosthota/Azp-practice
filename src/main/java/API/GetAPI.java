package API;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class GetAPI {

    private Response getAPI(String id, String token) {

        RequestSpecification requestSpecification = given()
                .header("Authorization", token)
                .when();

        if (id != null) {
            requestSpecification.queryParam("id", id);
        }

        return requestSpecification
                .get("/object")
                .then()
                .extract().response();
    }

    public Response getAPIValidations(String id, String token) {
        return getAPI(id, token);
    }

    public Response getAPIValidations(String token) {
        return getAPI(null, token);
    }
}
