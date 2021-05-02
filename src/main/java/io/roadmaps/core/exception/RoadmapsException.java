package io.roadmaps.core.exception;

public class RoadmapsException extends RuntimeException {

    private String explanation;

    public String getExplanation() {
        return explanation;
    }

    public RoadmapsException(String explanation) {
        this.explanation = explanation;
    }

    public RoadmapsException(String explanation, String message) {
        super(message);
        this.explanation = explanation;
    }

    public RoadmapsException(String explanation, String message, Throwable cause) {
        super(message, cause);
        this.explanation = explanation;
    }

    public RoadmapsException(String explanation, Throwable cause) {
        super(cause);
        this.explanation = explanation;
    }

    public RoadmapsException(String explanation, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.explanation = explanation;
    }
}