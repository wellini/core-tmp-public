package cc.roadmaps.extauth.exceptions;

public class AuthenticationNotSupportedExternalAuthenticationException extends ExternalAuthenticationException {

    public AuthenticationNotSupportedExternalAuthenticationException() {
        super("Authentication strategy not supported");
    }
}
