package io.roadmaps.core.rest.handler;

import io.roadmaps.core.exception.RoadmapsException;
import io.roadmaps.core.rest.common.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RoadmapsException.class})
    public ResponseEntity<ApiErrorDto> handleManagedException(RoadmapsException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        ApiErrorDto dto = new ApiErrorDto(ex.getExplanation());
        HttpStatus status = getHttpStatus(ex);
        return new ResponseEntity<>(dto, status);
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

    private HttpStatus getHttpStatus(Exception exception) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class))
                .map(ResponseStatus::code)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

