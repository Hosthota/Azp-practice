package com.api.verifications;

import com.api.utils.JSONUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import com.api.responses.AddEntryNameRequest;
import com.api.responses.AddEntryNameResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import com.api.utils.JsonSchemaCompare;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddEntryNameVerifications {
    public Response response;
    private final Log log = LogFactory.getLog(this.getClass().getName());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AddEntryNameVerifications(Response response){
        this.response = response;
    }

    @Description("Checks success status codes for POST API")
    public AddEntryNameVerifications verifySuccessStatusCode() {
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected 200 as status code, but received "+ response.getStatusCode());

        return this;
    }

    public AddEntryNameVerifications verifyJSONSchema() {
        JsonSchemaCompare jsonSchemaCompare = new JsonSchemaCompare();
        jsonSchemaCompare.compareResponseWithJsonSchema(response, "AddEntryName.json");
        log.info("Verify JSON schema method for POST API is completed");
        return  this;
    }


    public AddEntryNameVerifications verifyEntryNameFromRequestAndResponse(AddEntryNameRequest addEntryNameRequest) {
        AddEntryNameResponse addEntryNameResponse = response.as(AddEntryNameResponse.class);
        String entryName = addEntryNameRequest.getEntryName();
        Assert.assertEquals(entryName, addEntryNameResponse.getInnerObject().getEntryName(),
                "Expected "+entryName+" but received "+ addEntryNameResponse.getInnerObject().getEntryName());

        return this;

    }

    public AddEntryNameVerifications verifyGeneratedCreatedDateMatchesWithExpectedDate() {
        AddEntryNameResponse addEntryNameResponse = response.as(AddEntryNameResponse.class);
        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.format(formatter);
        Assert.assertEquals(addEntryNameResponse.getInnerObject().getCreationTime(), dateString,
                "Expected creationTime to be " + dateString + " but received " + addEntryNameResponse.getInnerObject().getCreationTime());

        return this;
    }

    public void verifyGeneratedEndDateMatchesWithExpectedDate() {
        LocalDate endTime = LocalDate.now().plusDays(1);
        String endTimeString = endTime.format(formatter);
        AddEntryNameResponse addEntryNameResponse = response.as(AddEntryNameResponse.class);
        Assert.assertEquals(addEntryNameResponse.getInnerObject().getEndTime(), endTimeString,
                "Expected endTime to be"+endTimeString +" but received" + addEntryNameResponse.getInnerObject().getEndTime());

    }

    public AddEntryNameVerifications verifyStatusCodeForDuplicateEntryName() {
        Assert.assertEquals(response.getStatusCode(), 409,
                "Expected 409 as status code, but received "+ response.getStatusCode());

        return this;
    }

    public void verifyErrorMessageOnDuplicateEntryName() {
        Assert.assertEquals(response.asString(), "Object with provided name already exists",
                "Expected Message is 'Object with provided name already exists' but received "+ response.asString());

    }

    public void verifyStatusCodeWithInvalidAuth() {
        Assert.assertEquals(response.getStatusCode(), 401,
                "Expected status code 401 but Actual status code is "+ response.getStatusCode());

    }

    public void verifyStatusCodeWhenNoRequestBodyIsSent() {
        Assert.assertEquals(response.statusCode(), 400,
                "Expected 400 status code but received " + response.getStatusCode());

    }

    public void verifyStatusCodeWhenInvalidContentTypeIsSent() {
        Assert.assertEquals(response.getStatusCode(), 415,
                "Expected status code 415 but received "+response.getStatusCode());
    }

    public String returnIdFromResponse() {
        String responseBody = response.asString();
        System.out.println(responseBody +"13");
        JsonPath responseBodyJson = new JsonPath(responseBody);
        return responseBodyJson.get("id");
    }
}
