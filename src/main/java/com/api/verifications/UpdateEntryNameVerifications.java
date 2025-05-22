package com.api.verifications;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.api.responses.UpdateEntryNameRequest;
import com.api.responses.UpdateEntryNameResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import com.api.utils.JsonSchemaCompare;

public class UpdateEntryNameVerifications {
    public Response response;
    private final Log log = LogFactory.getLog(this.getClass().getName());

    public UpdateEntryNameVerifications(Response response){
        this.response = response;
    }


    public void verifyRequestAndResponseOfPatchApi(UpdateEntryNameRequest updateEntryNameRequest) {
        UpdateEntryNameResponse updateEntryNameResponse = response.as(UpdateEntryNameResponse.class);
        if(updateEntryNameRequest.getPatchFieldName().equalsIgnoreCase("endTime")){
            String endTime = updateEntryNameRequest.getValue();
            Assert.assertEquals(updateEntryNameResponse.getEndTime(), endTime,
                    "Expected end time is :"+ updateEntryNameRequest +" but actual end time is :"+ updateEntryNameResponse.getEndTime());
        } else if (updateEntryNameRequest.getPatchFieldName().equalsIgnoreCase("entryName")) {
            String entryName = updateEntryNameRequest.getValue();
            Assert.assertEquals(entryName, updateEntryNameResponse.getEntryName(),
                    "Expected "+entryName+" but received "+ updateEntryNameResponse.getEntryName());

        }

    }

    public UpdateEntryNameVerifications verifyPatchSuccessStatusCode() {
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but received "+response.getStatusCode());
        return this;
    }

    public  UpdateEntryNameVerifications verifyJSONSchema() {
        JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();
        jsonSchemaCompare.compareResponseWithJsonSchema(response,"UpdateEntryName.json");
        log.info("Verify JSON Schema with response body method completed");
        return this;
    }

    public UpdateEntryNameVerifications verifyStatusCodeOnBadRequest() {
        Assert.assertEquals(response.getStatusCode(), 400,
                "Expected status code 400 but received "+response.getStatusCode());

        return this;
    }

    public void verifyMessageOnInvalidPatchFieldName() {
        JsonPath responseJson = new JsonPath(response.asString());
        Assert.assertEquals(responseJson.getString("errorMessage"), "Provided Patch Field is not supported"
                , "Expected message 'Provided Patch Field is not supported' but actual value is "+ responseJson.getString("errorMessage"));
    }

    public void verifyStatusCodeOnInvalidAuth(String token) {
        Assert.assertEquals(response.statusCode(), 401,
                "Expected 401 status code but received for the auth value "+token +" " + response.getStatusCode());
    }

    public void verifyStatusCodeOnInvalidID(String id) {
        Assert.assertEquals(response.statusCode(), 404,
                "Expected 404 status code but received " + response.getStatusCode());
    }

    public void verifyStatusCodeOnInvalidContentType() {
        Assert.assertEquals(response.getStatusCode(), 415,
                "Expected 415 status code but received "+response.getStatusCode());
    }
}
