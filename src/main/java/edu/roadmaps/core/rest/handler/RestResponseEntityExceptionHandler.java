package edu.roadmaps.core.rest.handler;

import edu.roadmaps.core.model.exception.ManagedException;
import edu.roadmaps.core.model.exception.NotFoundException;
import edu.roadmaps.core.model.exception.ValidationException;
import edu.roadmaps.core.rest.dto.api.ApiErrorDto;
import edu.roadmaps.core.security.exception.ManagedSecurityException;
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

    @ExceptionHandler(value = {ManagedException.class})
    public ResponseEntity<ApiErrorDto> handleManagedException(ManagedException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ok(new ApiErrorDto(ex.getExplanation()));
    }

    @ExceptionHandler(value = {ManagedSecurityException.class})
    public ResponseEntity<ApiErrorDto> handleManagedException(ManagedSecurityException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorDto(ex.getExplanation()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorDto(ex.getExplanation()));
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ApiErrorDto> handleValidationException(ValidationException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorDto(ex.getExplanation()));
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

