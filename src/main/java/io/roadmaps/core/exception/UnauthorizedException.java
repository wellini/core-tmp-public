package io.roadmaps.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RoadmapsException {

    public UnauthorizedException() {
        super("Unauthorized");
    }
}
