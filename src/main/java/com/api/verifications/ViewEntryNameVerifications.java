package com.api.verifications;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import com.api.utils.JsonSchemaCompare;

public class ViewEntryNameVerifications {
    public Response response;
    private final Log log = LogFactory.getLog(this.getClass().getName());
    JsonPath responseJson;

    public ViewEntryNameVerifications(Response response) {
        this.response = response;
    }

    public ViewEntryNameVerifications verifyStatusCode(){
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected 200 as status code, but received "+ response.getStatusCode());
        return this;
    }

    public void verifyJSONSchema(){
        JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();
        jsonSchemaCompare.compareResponseWithJsonSchema(response, "ViewEntryName.json");
        log.info("Verify Json Schema for get API method completed");
    }

    public ViewEntryNameVerifications verifyInvalidIDStatusCode() {
        Assert.assertEquals(response.statusCode(), 404,
                "Expected status code is 404 for invalid ID, received status code is "+ response.statusCode());
        return this;
    }

    public void verifyErrorMessageOnInvalidID() {
        responseJson = new JsonPath(response.asString());
        Assert.assertEquals(responseJson.getString("errorMessage"), "Object with provided ID was not found",
                "Expected error message is 'Object with provided ID was not found' but received "+ responseJson.get("errorMessage"));


    }

    public ViewEntryNameVerifications verifyInvalidAuthorizationStatusCode() {
        Assert.assertEquals(response.statusCode(), 401,
                "Expected status code is 401 for invalid authorization, received status code is "+ response.statusCode());
        return this;
    }

    public void verifyErrorMessageOnInvalidAuth() {
        responseJson = new JsonPath(response.asString());
        Assert.assertEquals(responseJson.getString("errorMessage"), "Invalid or missing header.",
                "Expected error message is 'Invalid or missing header.' but received " + responseJson.get("errorMessage"));

    }


    public void verifyStatusCodeWhenNoQueryParameterISPassed() {
        Assert.assertEquals(response.statusCode(), 400,
                "Expected status code is 400 for invalid authorization, received status code is "+ response.statusCode());
    }
}
