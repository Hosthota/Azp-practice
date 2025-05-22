package com.api.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEntryNameRequest {
    private String entryName;

    public AddEntryNameRequest(String entryName) {
        this.entryName = entryName;
    }
}
