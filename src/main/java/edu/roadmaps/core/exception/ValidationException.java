package edu.roadmaps.core.exception;

public class ValidationException extends RoadmapsException {

    public ValidationException(String explanation) {
        super(explanation);
    }

    public ValidationException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}