package io.roadmaps.core.exception;

public class AuthException extends RoadmapsException {

    public AuthException() {
        super("It is not possible to authorize, try later");
    }

}
