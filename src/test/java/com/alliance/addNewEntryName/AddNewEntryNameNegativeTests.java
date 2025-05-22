package com.alliance.addNewEntryName;

import com.api.constants.Constants;
import com.api.data.DataProviderClass;
import com.api.utils.GenerateRandomValue;
import io.qameta.allure.Feature;
import com.api.responses.AddEntryNameRequest;
import org.testng.annotations.Test;
import com.api.requests.CreateEntryName;


@Feature("Add entry Name using POST Api negative scenarios")
public class AddNewEntryNameNegativeTests {

    CreateEntryName createEntryName = new CreateEntryName();
    GenerateRandomValue generateRandomValue = new GenerateRandomValue();
    String entryName;

    @Test(description = "Updating Existing 'entryName' value and verifying the status code")
    public void verifyResponseWhenEntryNameIsRepeated() {
        entryName = generateRandomValue.generateEntryName();
        AddEntryNameRequest addEntryNameRequest = new AddEntryNameRequest(entryName);
        createEntryName
                .postAPIValidations(Constants.BEARER_TOKEN, Constants.CONTENT_TYPE, addEntryNameRequest)
                .verifySuccessStatusCode();

        createEntryName.
                postAPIValidations(Constants.BEARER_TOKEN, Constants.CONTENT_TYPE, addEntryNameRequest)
                .verifyStatusCodeForDuplicateEntryName()
                .verifyErrorMessageOnDuplicateEntryName();
    }

    @Test(dataProvider = "InvalidAuth", dataProviderClass = DataProviderClass.class,
            description = "Passing in valid and null values for authorization key")
    public void verifyInvalidAuth(String auth){
        entryName = generateRandomValue.generateEntryName();
        AddEntryNameRequest addEntryNameRequest = new AddEntryNameRequest(entryName);
        createEntryName
                .postAPIValidations(auth,Constants.CONTENT_TYPE, addEntryNameRequest)
                .verifyStatusCodeWithInvalidAuth();
    }

    @Test(description = "Not passing the body and verifying the response")
    public void verifyResponseWhenBodyIsNotSent() {
         createEntryName.postAPIValidations()
                 .verifyStatusCodeWhenNoRequestBodyIsSent();
    }

    @Test(description = "Passing invalid content type and verifying the response")
    public void verifyResponseWhenInvalidContentTypeIsSent() {
        entryName = generateRandomValue.generateEntryName();
        AddEntryNameRequest addEntryNameRequest = new AddEntryNameRequest(entryName);
       createEntryName.postAPIValidations(addEntryNameRequest)
               .verifyStatusCodeWhenInvalidContentTypeIsSent();
    }
}
