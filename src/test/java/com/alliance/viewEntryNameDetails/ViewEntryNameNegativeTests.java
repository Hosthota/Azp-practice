package com.alliance.viewEntryNameDetails;

import com.api.constants.Constants;
import com.api.data.DataProviderClass;
import com.api.requests.CreateEntryName;
import com.api.responses.AddEntryNameRequest;
import com.api.utils.GenerateRandomValue;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.requests.ViewEntryName;

@Feature("View entry name using Get API negative scenarios")
public class ViewEntryNameNegativeTests {

    ViewEntryName viewEntryName = new ViewEntryName();
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

    @Test(dataProvider ="InvalidId", dataProviderClass = DataProviderClass.class,
            description = "Passing invalid and null values for query parameter")
    public void verifyIdNegativeScenario(String id){
        viewEntryName.getAPIValidations(id, Constants.BEARER_TOKEN)
                .verifyInvalidIDStatusCode()
                .verifyErrorMessageOnInvalidID();
    }

    @Test(dataProvider = "InvalidAuth", dataProviderClass = DataProviderClass.class,
            description = "Passing invalid and no authorization key")
    public void verifyAuthNegativeScenario(String auth) {
        viewEntryName.getAPIValidations(id, auth)
                .verifyInvalidAuthorizationStatusCode()
                .verifyErrorMessageOnInvalidAuth();
    }

    @Test(description = "Not passing Query parameter")
    public void verifyWithoutPassingQueryParam() {
        viewEntryName.getAPIValidations(Constants.BEARER_TOKEN)
                .verifyStatusCodeWhenNoQueryParameterISPassed();
    }
}
