package cc.roadmaps.core.service.integrations.web.rest.api.handler;

import cc.roadmaps.core.domain.exceptions.DomainException;
import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.exceptions.NotEnoughPermissionsDomainException;
import cc.roadmaps.core.domain.exceptions.UnauthorizedDomainException;
import cc.roadmaps.core.service.exceptions.RoadmapsServiceException;
import cc.roadmaps.core.service.integrations.web.rest.api.common.dtos.ExplainedErrorResponse;
import cc.roadmaps.core.service.integrations.web.rest.api.common.dtos.ValidationErrorResponse;
import cc.roadmaps.validation.exceptions.ValidationException;
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

    @ExceptionHandler(value = {RoadmapsServiceException.class})
    public ResponseEntity<ExplainedErrorResponse> handleManagedException(RoadmapsServiceException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        ExplainedErrorResponse dto = ExplainedErrorResponse.create(ex.getMessage());
        HttpStatus status = getHttpStatus(ex);
        return new ResponseEntity<>(dto, status);
    }

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ExplainedErrorResponse> handleValidationException(DomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExplainedErrorResponse.create(ex.getMessage()));
    }

    @ExceptionHandler(value = {EntityNotFoundDomainException.class})
    public ResponseEntity<ExplainedErrorResponse> handleValidationException(EntityNotFoundDomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExplainedErrorResponse.create(ex.getMessage()));
    }

    @ExceptionHandler(value = {NotEnoughPermissionsDomainException.class})
    public ResponseEntity<ExplainedErrorResponse> handleValidationException(NotEnoughPermissionsDomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExplainedErrorResponse.create(ex.getMessage()));
    }

    @ExceptionHandler(value = {UnauthorizedDomainException.class})
    public ResponseEntity<ExplainedErrorResponse> handleValidationException(UnauthorizedDomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExplainedErrorResponse.create(ex.getMessage()));
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ValidationErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {
        log.warn("400, Validation exception handled");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrorResponse.create(ex.getReport()));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ExplainedErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExplainedErrorResponse.create("Internal server error :("));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExplainedErrorResponse.create("Internal server error :("));
    }

    private HttpStatus getHttpStatus(Exception exception) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class))
                .map(ResponseStatus::code)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

