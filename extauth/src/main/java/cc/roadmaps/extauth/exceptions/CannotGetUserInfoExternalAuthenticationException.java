package cc.roadmaps.extauth.exceptions;

public class CannotGetUserInfoExternalAuthenticationException extends ExternalAuthenticationException {

    private static final String MESSAGE = "Failed to get userinfo";

    public CannotGetUserInfoExternalAuthenticationException(String message) {
        super(MESSAGE);
    }

    public CannotGetUserInfoExternalAuthenticationException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
