package cc.roadmaps.extauth.exceptions;

public class CannotGetUserIdExternalAuthenticationException extends ExternalAuthenticationException {

    public CannotGetUserIdExternalAuthenticationException(Throwable cause) {
        super("Cannot get user id", cause);
    }
}
