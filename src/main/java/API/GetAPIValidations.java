package API;

import io.restassured.response.Response;
import org.testng.Assert;

public class GetAPIValidations {

    public void verifyStatusCode(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code expected 200, but received "+response.getStatusCode());
    }

    public void verifyRequestBody(Response response) {
        assert response.body().jsonPath().get("entryName") instanceof String : "EntryName field is not in String pattern";
        assert response.body().jsonPath().get("creationTime") instanceof String : "Creation Time field is not in String pattern";
        assert response.body().jsonPath().get("endTime") instanceof String : "End time field is not in String pattern";

    }
}
