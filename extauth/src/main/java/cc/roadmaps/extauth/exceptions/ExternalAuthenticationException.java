package cc.roadmaps.extauth.exceptions;

public class ExternalAuthenticationException extends RuntimeException {

    public ExternalAuthenticationException(String message) {
        super(message);
    }

    public ExternalAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalAuthenticationException(Throwable cause) {
        super(cause);
    }

    public ExternalAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
