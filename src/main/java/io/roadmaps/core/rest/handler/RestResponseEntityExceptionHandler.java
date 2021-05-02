package io.roadmaps.core.rest.handler;

import io.roadmaps.core.exception.RoadmapsException;
import io.roadmaps.core.rest.common.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.ResponseEntity.ok;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RoadmapsException.class})
    public ResponseEntity<ApiErrorDto> handleManagedException(RoadmapsException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ok(new ApiErrorDto(ex.getExplanation()));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiErrorDto> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorDto("Internal server error :("));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorDto("Internal server error :("));
    }

}

