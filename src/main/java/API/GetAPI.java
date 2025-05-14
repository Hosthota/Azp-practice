package API;


import  io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetAPI {

    private Response getAPI(String id, String token) {
        if (id == null) {
            return given()
                    .header("Authorization", token)
                    .when()
                    .get("/object")
                    .then()
                    .log().all()
                    .extract().response();
        } else {
            return given()
                    .queryParam("id", id)
                    .header("Authorization", token)
                    .when()
                    .get("/object")
                    .then()
                    .log().all()
                    .extract().response();
        }
    }

    public Response getAPIValidations(String id, String token){
        return getAPI(id,token);
    }

    public Response getAPIValidations(String token){
        return getAPI(null,token);
    }
}
