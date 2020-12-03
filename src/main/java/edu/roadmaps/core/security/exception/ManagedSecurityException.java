package edu.roadmaps.core.security.exception;


import edu.roadmaps.core.model.exception.ManagedException;

public class ManagedSecurityException extends ManagedException {

    public ManagedSecurityException(String explanation) {
        super(explanation);
    }

    public ManagedSecurityException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}