package io.roadmaps.core.hrid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnresolvableHRIDException extends RuntimeException {

    public UnresolvableHRIDException() {
        super("Cannot resolve HDID");
    }
}
