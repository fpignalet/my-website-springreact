package com.core.eng;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 */
@ControllerAdvice
public class EngExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ EngGeneralException.class })
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "General Error", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
