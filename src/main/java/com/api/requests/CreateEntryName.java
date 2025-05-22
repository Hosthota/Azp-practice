package com.api.requests;

import com.api.BaseAPIClass;
import com.api.constants.Constants;
import com.api.responses.AddEntryNameRequest;
import com.api.utils.JSONUtils;
import com.api.verifications.AddEntryNameVerifications;
import io.restassured.RestAssured;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;


public class CreateEntryName extends BaseAPIClass {
    

    private AddEntryNameVerifications postAPI(String auth, String contentType, String postRequestBody) {
        requestSpecification = given()
                .baseUri("http://localhost:9000/")
                .header("Authorization", auth)
                .contentType(contentType);

        if (postRequestBody != null) {
            requestSpecification.body(postRequestBody);

        }
        response = requestSpecification
                .post("/object")
                .then()
                .log().all()
                .extract().response();

        return new AddEntryNameVerifications(response);
    }
    public AddEntryNameVerifications postAPIValidations(String auth, String contentType, AddEntryNameRequest postRequestBody){
                return postAPI(auth, contentType, JSONUtils.serialize(postRequestBody));
    }

    public AddEntryNameVerifications postAPIValidations(){
            return postAPI(Constants.BEARER_TOKEN, Constants.CONTENT_TYPE,null);
    }

    public AddEntryNameVerifications postAPIValidations( AddEntryNameRequest addEntryNameRequest){
            return postAPI(Constants.BEARER_TOKEN,"application/xml", JSONUtils.serialize(addEntryNameRequest));
    }

}
