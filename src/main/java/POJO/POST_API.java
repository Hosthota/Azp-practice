package POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class POST_API {
    private String entryName;

    public POST_API(String entryName) {
        this.entryName = entryName;
    }
}
