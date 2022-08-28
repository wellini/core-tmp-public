package io.roadmaps.core.integrations.web.rest.api.dto;

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
