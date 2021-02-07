package edu.roadmaps.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RoadmapsException {

    public EntityNotFoundException(String explanation) {
        super(explanation);
    }

    public EntityNotFoundException(String explanation, String message) {
        super(explanation, message);
    }

    public EntityNotFoundException(String explanation, String message, Throwable cause) {
        super(explanation, message, cause);
    }

    public EntityNotFoundException(String explanation, Throwable cause) {
        super(explanation, cause);
    }

    public EntityNotFoundException(String explanation, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(explanation, message, cause, enableSuppression, writableStackTrace);
    }
}

