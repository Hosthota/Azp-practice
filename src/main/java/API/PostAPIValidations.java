package API;

import POJO.POST_API;
import POJO.Post_Response;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostAPIValidations {

        public void verifyRequestBody(Post_Response postResponse, POST_API po) {
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

        String entryName = po.getEntryName();
        Assert.assertEquals(entryName, postResponse.getInnerObject().getEntryName(),
                "Expected "+entryName+" but received "+ postResponse.getInnerObject().getEntryName());

    }
}
