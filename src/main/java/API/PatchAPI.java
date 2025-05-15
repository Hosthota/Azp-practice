package API;


import POJO.PatchRequestBody;
import POJO.PatchResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PatchAPI {

    public void verifyRequestBody(PatchRequestBody patchRequestBody, PatchResponseBody patchResponseBody) {

        SoftAssert softAssert = new SoftAssert();
        if(patchRequestBody.getPatchFieldName().equalsIgnoreCase("endTime")){
           String endTime = patchRequestBody.getValue();
            softAssert.assertEquals(patchResponseBody.getEndTime(), endTime,
                   "Expected end time is :"+ patchRequestBody+" but actual end time is :"+ patchResponseBody.getEndTime());
        } else if (patchRequestBody.getPatchFieldName().equalsIgnoreCase("entryName")) {
            String entryName = patchRequestBody.getValue();
            softAssert.assertEquals(entryName, patchResponseBody.getEntryName(),
                    "Expected "+entryName+" but received "+ patchResponseBody.getEntryName());

        }
        softAssert.assertAll();
    }


    private <T> Response patchAPI(String id, String auth, T patchRequestBody, String contentType){

        RequestSpecification requestSpecification = given()
                .header("Authorization", auth)
                .contentType(contentType)
                .pathParam("id", id);

        if(patchRequestBody != null) {
            requestSpecification.body(patchRequestBody);
        }
            return requestSpecification
                    .patch("/object/patch/{id}")
                    .then()
                    .extract().response();
    }

    public Response patchAPIValidations(String id, String auth, PatchRequestBody patchRequestBody, String contentType){
        return patchAPI(id,auth,patchRequestBody,contentType);
    }

    //no body sent
    public Response patchAPIValidations(String id, String auth,String contentType){
        return patchAPI(id,auth,null,contentType);
    }

    //invalid contentType
    public Response patchAPIValidations(String id, String auth, PatchRequestBody patchRequestBody){
        return patchAPI(id, auth, patchRequestBody.getPatchFieldName(),"application/xml");
    }
}
