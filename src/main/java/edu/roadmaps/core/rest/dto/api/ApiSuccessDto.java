package edu.roadmaps.core.rest.dto.api;

public class ApiSuccessDto {
    private String message;

    public ApiSuccessDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
