package edu.roadmaps.core.security.exception;


import edu.roadmaps.core.exception.RoadmapsException;

public class RoadmapsSecurityException extends RoadmapsException {

    public RoadmapsSecurityException(String explanation) {
        super(explanation);
    }

    public RoadmapsSecurityException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}