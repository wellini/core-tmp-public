package io.roadmaps.core.integrations.web.rest.api.dto;

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
