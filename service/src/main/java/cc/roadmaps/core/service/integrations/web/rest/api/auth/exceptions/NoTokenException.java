package cc.roadmaps.core.service.integrations.web.rest.api.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoTokenException extends AuthorizationException {

    public NoTokenException(String message) {
        super(message);
    }
}
