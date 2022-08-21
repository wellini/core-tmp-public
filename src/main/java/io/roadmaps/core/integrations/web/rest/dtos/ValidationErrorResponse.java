package io.roadmaps.core.integrations.web.rest.dtos;

import io.roadmaps.core.validation.ValidationReport;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ValidationErrorResponse extends AbstractErrorResponse {

    private final Map<String, List<String>> errors;

    private ValidationErrorResponse(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public static ValidationErrorResponse create(ValidationReport report) {
        return new ValidationErrorResponse(report.getErrors());
    }
}
