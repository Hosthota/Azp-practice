package com.alliance.viewEntryNameDetails;

import com.api.constants.Constants;
import com.api.requests.CreateEntryName;
import com.api.responses.AddEntryNameRequest;
import com.api.utils.GenerateRandomValue;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.requests.ViewEntryName;


@Feature("View entry name using Get API positive scenarios")
public class ViewEntryNamePositiveTests{

    private static String id;
    ViewEntryName viewEntryName = new ViewEntryName();

    @BeforeClass
    public void getIdFromPostRequest(){
        CreateEntryName createEntryName = new CreateEntryName();
        GenerateRandomValue generateRandomValue = new GenerateRandomValue();
        String entryName = generateRandomValue.generateEntryName();
        AddEntryNameRequest addEntryNameRequest = new AddEntryNameRequest(entryName);
        id = createEntryName.postAPIValidations(Constants.BEARER_TOKEN, Constants.CONTENT_TYPE, addEntryNameRequest)
                .returnIdFromResponse();
    }

    @Test(description = "Verify response by passing all the valid data")
    public void verifyPositiveResponseWithValidDetails(){
      viewEntryName.getAPIValidations(id, Constants.BEARER_TOKEN)
              .verifyStatusCode()
              .verifyJSONSchema();
    }
}
