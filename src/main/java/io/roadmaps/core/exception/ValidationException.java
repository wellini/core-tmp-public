package io.roadmaps.core.exception;

import io.roadmaps.core.validation.ValidationReport;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RoadmapsException {

    private final ValidationReport report;

    public ValidationException(ValidationReport report) {
        super("Validation error");
        this.report = report;
    }
}