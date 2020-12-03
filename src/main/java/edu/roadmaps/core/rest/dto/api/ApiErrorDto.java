package edu.roadmaps.core.rest.dto.api;

public class ApiErrorDto {
    private String message;

    public ApiErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
