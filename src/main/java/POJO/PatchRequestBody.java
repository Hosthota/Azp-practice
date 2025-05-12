package POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchRequestBody {

    String patchFieldName;
    String value;

    public PatchRequestBody(String patchFieldName, String value) {
        this.patchFieldName = patchFieldName;
        this.value=value;
    }
}
