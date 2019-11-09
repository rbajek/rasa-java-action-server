package io.github.rbajek.rasa.action.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Rafał Bajek
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Throwable.class})
    protected ResponseEntity<Object> unknownErrors(RuntimeException ex, WebRequest request) {
        log.error("An internal error has occurred", ex);
        return handleExceptionInternal(ex, "Internal server error",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
