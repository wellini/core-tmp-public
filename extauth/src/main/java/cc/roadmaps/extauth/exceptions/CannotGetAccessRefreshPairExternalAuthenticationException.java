package cc.roadmaps.extauth.exceptions;

public class CannotGetAccessRefreshPairExternalAuthenticationException extends ExternalAuthenticationException {

    private static final String MESSAGE = "Failed to get access/refresh tokens pair";

    public CannotGetAccessRefreshPairExternalAuthenticationException() {
        super(MESSAGE);
    }

    public CannotGetAccessRefreshPairExternalAuthenticationException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
