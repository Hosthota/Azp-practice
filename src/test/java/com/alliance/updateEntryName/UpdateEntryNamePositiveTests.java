package com.alliance.updateEntryName;

import com.api.constants.Constants;
import com.api.requests.CreateEntryName;
import com.api.responses.AddEntryNameRequest;
import com.api.utils.GenerateRandomValue;
import io.qameta.allure.Feature;
import com.api.responses.UpdateEntryNameRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.requests.UpdateExistingEntryName;

@Feature("Update entry name or end time using Patch API negative scenarios")
public class UpdateEntryNamePositiveTests {

    UpdateExistingEntryName updateExistingEntryName = new UpdateExistingEntryName();
    GenerateRandomValue generateRandomValue = new GenerateRandomValue();
    String patchFieldName;
    private static String id;

    @BeforeClass
    public void getIdFromPostRequest(){
        CreateEntryName createEntryName = new CreateEntryName();
        GenerateRandomValue generateRandomValue = new GenerateRandomValue();
        String entryName = generateRandomValue.generateEntryName();
        AddEntryNameRequest addEntryNameRequest = new AddEntryNameRequest(entryName);
        id = createEntryName.postAPIValidations(Constants.BEARER_TOKEN, Constants.CONTENT_TYPE, addEntryNameRequest)
                .returnIdFromResponse();
    }

    @Test(description = "Verify the response by passing all the valid data by entering the patchFiledValue as entryname")
    public void verifyPositiveResponseWithValidDetailsByUpdatingEntryName() {
        String entryName  = generateRandomValue.generateEntryNameForPatch();
        patchFieldName = "entryName";
        UpdateEntryNameRequest updateEntryNameRequest = new UpdateEntryNameRequest(patchFieldName,entryName );
        updateExistingEntryName.updateEntryNameValidations(id, Constants.BEARER_TOKEN, updateEntryNameRequest, Constants.CONTENT_TYPE)
                .verifyPatchSuccessStatusCode()
                .verifyJSONSchema()
                .verifyRequestAndResponseOfPatchApi(updateEntryNameRequest);
    }

    @Test(description = "Verify response by passing all the valid data by passing patchFiledName as endTime")
    public void verifyPositiveResponseWithValidDetailsByUpdatingEndTime(){
        String endTime  = generateRandomValue.generateEndTimeForPatch();
        patchFieldName = "endTime";
        UpdateEntryNameRequest updateEntryNameRequest = new UpdateEntryNameRequest(patchFieldName, endTime);
        updateExistingEntryName.updateEntryNameValidations(id, Constants.BEARER_TOKEN, updateEntryNameRequest,Constants.CONTENT_TYPE)
                .verifyPatchSuccessStatusCode()
                .verifyJSONSchema()
                .verifyRequestAndResponseOfPatchApi(updateEntryNameRequest);
    }
}
