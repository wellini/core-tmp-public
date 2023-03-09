package cc.roadmaps.core.service.integrations.web.rest.api.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends AuthorizationException {

    public UnauthorizedException() {
        super("Unauthorized");
    }
}
