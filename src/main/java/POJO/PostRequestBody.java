package POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestBody {
    private String entryName;

    public PostRequestBody(String entryName) {
        this.entryName = entryName;
    }
}
