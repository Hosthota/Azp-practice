package com.api.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateEntryNameRequest implements Serializable {

    String patchFieldName;
    String value;

    public UpdateEntryNameRequest(String patchFieldName, String value) {
        this.patchFieldName = patchFieldName;
        this.value=value;
    }
}
