package io.roadmaps.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotEnoughPermissionsException extends RoadmapsException {

    public NotEnoughPermissionsException() {
        super("Not enough permissions");
    }
}
