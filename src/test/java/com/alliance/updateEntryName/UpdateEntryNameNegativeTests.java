package com.alliance.updateEntryName;

import com.api.constants.Constants;
import com.api.data.DataProviderClass;
import com.api.requests.CreateEntryName;
import com.api.responses.AddEntryNameRequest;
import com.api.utils.GenerateRandomValue;
import io.qameta.allure.Feature;
import com.api.responses.UpdateEntryNameRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.requests.UpdateExistingEntryName;

@Feature("Update entry name or end time using Patch API")
public class UpdateEntryNameNegativeTests{
    UpdateExistingEntryName updateExistingEntryName = new UpdateExistingEntryName();
    GenerateRandomValue generateRandomValue = new GenerateRandomValue();
    String entryName  = generateRandomValue.generateEntryNameForPatch();
    private final String patchFieldName = "entryName";
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

    @Test(description = "Passing Invalid 'PatchFieldName' value in the request body")
    public void verifyResponseByPassingInvalidPatchFieldNameValue() {

        UpdateEntryNameRequest updateEntryNameRequest = new UpdateEntryNameRequest("entryName1", entryName);
        updateExistingEntryName.updateEntryNameValidations(id, Constants.BEARER_TOKEN, updateEntryNameRequest,Constants.CONTENT_TYPE)
                .verifyStatusCodeOnBadRequest()
                .verifyMessageOnInvalidPatchFieldName();
    }

    @Test(description = "Not passing the request body")
    public void verifyResponseWithNoBody(){
        updateExistingEntryName.updateEntryNameValidations(id,Constants.BEARER_TOKEN,Constants.CONTENT_TYPE)
                        .verifyStatusCodeOnBadRequest();
    }

    @Test(dataProvider ="InvalidAuth", dataProviderClass = DataProviderClass.class,
            description = "passing invalid and null values for authorization key")
    public void verifyInvalidAuth(String token){
        UpdateEntryNameRequest updateEntryNameRequest = new UpdateEntryNameRequest(patchFieldName, entryName);
        updateExistingEntryName.updateEntryNameValidations(id,token, updateEntryNameRequest,Constants.CONTENT_TYPE)
                .verifyStatusCodeOnInvalidAuth(token);
    }


    @Test(dataProvider ="InvalidId", dataProviderClass = DataProviderClass.class,
            description = "passing invalid or null values for path parameter")
    public void verifyInvalidId(String id){
        UpdateEntryNameRequest updateEntryNameRequest = new UpdateEntryNameRequest(patchFieldName, entryName);
        updateExistingEntryName.updateEntryNameValidations(id,Constants.BEARER_TOKEN, updateEntryNameRequest,Constants.CONTENT_TYPE)
                .verifyStatusCodeOnInvalidID(id);

    }

    @Test(description = "Not passing body and verifying the PATCH api ")
    public void verifyResponseByNotPassingBody() {
        updateExistingEntryName.updateEntryNameValidations(id,Constants.BEARER_TOKEN,Constants.CONTENT_TYPE)
                .verifyStatusCodeOnBadRequest();
    }

    @Test(description = "Not passing valid content type")
    public void verifyResponseByInvalidContentType(){
        UpdateEntryNameRequest updateEntryNameRequest = null;
        updateExistingEntryName.updateEntryNameValidations(id,Constants.BEARER_TOKEN,  (updateEntryNameRequest))
                .verifyStatusCodeOnInvalidContentType();
    }
}
