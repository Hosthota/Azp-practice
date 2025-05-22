package com.alliance.addNewEntryName;

import com.api.constants.Constants;
import com.api.utils.GenerateRandomValue;
import io.qameta.allure.Feature;
import com.api.responses.AddEntryNameRequest;
import org.testng.annotations.Test;
import com.api.requests.CreateEntryName;


@Feature("Add entry name using Post API positive scenarios")
public class AddNewEntryNamePositiveTest  {
    String entryName;
    GenerateRandomValue generateRandomValue = new GenerateRandomValue();
    CreateEntryName createEntryName = new CreateEntryName();

    @Test(description = "Verify response by passing valid details")
    public void verifyPositiveResponseWithValidDetails() {
        entryName = generateRandomValue.generateEntryName();
        AddEntryNameRequest addEntryNameRequest = new AddEntryNameRequest(entryName);
        createEntryName.postAPIValidations(Constants.BEARER_TOKEN, Constants.CONTENT_TYPE, addEntryNameRequest)
                .verifySuccessStatusCode()
                .verifyJSONSchema()
                .verifyEntryNameFromRequestAndResponse(addEntryNameRequest)
                .verifyGeneratedCreatedDateMatchesWithExpectedDate()
                .verifyGeneratedEndDateMatchesWithExpectedDate();
    }
}
