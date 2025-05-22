package com.api.requests;


import com.api.BaseAPIClass;
import com.api.verifications.ViewEntryNameVerifications;
import static io.restassured.RestAssured.given;

public class ViewEntryName extends BaseAPIClass {

    private ViewEntryNameVerifications getAPI(String id, String token) {
        requestSpecification = given()
                .baseUri("http://localhost:9000/")
                .header("Authorization", token)
                .when();

        if (id != null) {
            requestSpecification.queryParam("id", id);
        }

       response =  requestSpecification
                .get("/object")
                .then()
                .extract().response();
        return new ViewEntryNameVerifications(response);

    }

    //update methodName
    public ViewEntryNameVerifications getAPIValidations(String id, String token) {
        return getAPI(id, token);
    }

    public ViewEntryNameVerifications getAPIValidations(String token) {
        return getAPI(null, token);
    }
}
