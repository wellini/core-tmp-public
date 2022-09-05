package cc.roadmaps.validation.exceptions;

import cc.roadmaps.validation.api.ValidationReport;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final ValidationReport report;

    public ValidationException(ValidationReport report) {
        super("Validation error");
        this.report = report;
    }
}
