package cc.roadmaps.core.service.integrations.web.rest.api.handler;

import cc.roadmaps.core.domain.exceptions.DomainException;
import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.exceptions.NotEnoughPermissionsDomainException;
import cc.roadmaps.core.domain.exceptions.UnauthorizedDomainException;
import cc.roadmaps.core.service.exceptions.RoadmapsServiceException;
import cc.roadmaps.core.service.integrations.web.rest.api.common.dtos.ExplainedErrorResponse;
import cc.roadmaps.core.service.integrations.web.rest.api.common.dtos.ValidationErrorResponse;
import cc.roadmaps.extauth.exceptions.AuthenticationNotSupportedExternalAuthenticationException;
import cc.roadmaps.extauth.exceptions.ExternalAuthenticationException;
import cc.roadmaps.validation.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExplainedErrorResponse handleValidationException(DomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ExplainedErrorResponse.create(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundDomainException.class})
    public ExplainedErrorResponse handleValidationException(EntityNotFoundDomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ExplainedErrorResponse.create(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {NotEnoughPermissionsDomainException.class})
    public ExplainedErrorResponse handleValidationException(NotEnoughPermissionsDomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ExplainedErrorResponse.create(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {UnauthorizedDomainException.class})
    public ExplainedErrorResponse handleValidationException(UnauthorizedDomainException ex, WebRequest request) {
        log.warn("Domain exception handled");
        return ExplainedErrorResponse.create(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {ExternalAuthenticationException.class})
    public ExplainedErrorResponse handleRuntimeException(ExternalAuthenticationException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        ex.printStackTrace();
        return ExplainedErrorResponse.create("Internal server error :(");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {AuthenticationNotSupportedExternalAuthenticationException.class})
    public ExplainedErrorResponse handleRuntimeException(AuthenticationNotSupportedExternalAuthenticationException ex, WebRequest request) {
        log.warn("RestResponseEntityExceptionHandler", ex);
        ex.printStackTrace();
        return ExplainedErrorResponse.create(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    public ValidationErrorResponse handleValidationException(ValidationException ex, WebRequest request) {
        log.warn("400, Validation exception handled");
        return ValidationErrorResponse.create(ex.getReport());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    public ExplainedErrorResponse handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("RestResponseEntityExceptionHandler", ex);
        ex.printStackTrace();
        return ExplainedErrorResponse.create("Internal server error :(");
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

