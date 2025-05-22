package com.api.requests;


import com.api.BaseAPIClass;
import com.api.responses.UpdateEntryNameRequest;
import com.api.utils.JSONUtils;
import com.api.verifications.UpdateEntryNameVerifications;
import static io.restassured.RestAssured.given;


public class UpdateExistingEntryName extends BaseAPIClass {

    private UpdateEntryNameVerifications patchAPI(String id, String auth, String patchRequestBody, String contentType){
        requestSpecification = given()
                .baseUri("http://localhost:9000/")
                .header("Authorization", auth)
                .contentType(contentType)
                .pathParam("id", id);

        if(patchRequestBody != null) {
            requestSpecification.body(patchRequestBody);
        }
        response = requestSpecification
                .log().all()
                    .patch("/object/patch/{id}")
                    .then()
                    .log().all()
                    .extract().response();

        return new UpdateEntryNameVerifications(response);
    }

    public UpdateEntryNameVerifications updateEntryNameValidations(String id, String auth, UpdateEntryNameRequest updateEntryNameRequest, String contentType){
        return patchAPI(id,auth, JSONUtils.serialize(updateEntryNameRequest),contentType);
    }

    //no body sent
    public UpdateEntryNameVerifications updateEntryNameValidations(String id, String auth, String contentType){
        return patchAPI(id,auth,null,contentType);
    }

    //invalid contentType
    public UpdateEntryNameVerifications updateEntryNameValidations(String id, String auth, UpdateEntryNameRequest updateEntryNameRequest){
        return patchAPI(id, auth, JSONUtils.serialize(updateEntryNameRequest),"application/xml");
    }
}
