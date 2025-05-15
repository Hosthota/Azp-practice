package API;

import POJO.PostRequestBody;
import POJO.PostResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class PostAPI {

        public void verifyRequestBody(PostResponseBody postResponse, PostRequestBody postRequestBody) {
        Assert.assertNotNull(postResponse.getId(), "Id value is NULL");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = currentDate.format(formatter);
        Assert.assertEquals(postResponse.getInnerObject().getCreationTime(), dateString,
                "Expected creationTime to be "+dateString +" but received "+postResponse.getInnerObject().getCreationTime());

        LocalDate endTime = LocalDate.now().plusDays(1);
        String endTimeString = endTime.format(formatter);
        Assert.assertEquals(postResponse.getInnerObject().getEndTime(), endTimeString,
                "Expected endTime to be"+endTimeString +" but received" +postResponse.getInnerObject().getEndTime());

        String entryName = postRequestBody.getEntryName();
        Assert.assertEquals(entryName, postResponse.getInnerObject().getEntryName(),
                "Expected "+entryName+" but received "+ postResponse.getInnerObject().getEntryName());

    }


    private <T> Response postAPI(String auth, String contentType, T postRequestBody) {

        RequestSpecification specification = given()
                .header("Authorization", auth)
                .contentType(contentType);

        if (postRequestBody != null) {
            specification.body(postRequestBody);

        }
        return specification
                .post("/object")
                .then()
                .extract().response();
    }

    public <T> Response postAPIValidations(String auth, String contentType, T postRequestBody){
                return postAPI(auth, contentType, postRequestBody);
    }

    public Response postAPIValidations(String auth, String contentType){
            return postAPI(auth, contentType,null);
    }


    public Response postAPIValidations(String auth, PostRequestBody postRequestBody){
            return postAPI(auth,"application/xml",postRequestBody.getEntryName());
    }

}
