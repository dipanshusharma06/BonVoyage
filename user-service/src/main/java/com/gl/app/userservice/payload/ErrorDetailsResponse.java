package com.gl.app.userservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ErrorDetailsResponse {
    private LocalDate timestamp;
    private String message;
    private String description;

    public ErrorDetailsResponse(LocalDate timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
