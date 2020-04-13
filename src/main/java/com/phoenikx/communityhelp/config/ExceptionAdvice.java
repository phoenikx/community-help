package com.phoenikx.communityhelp.config;

import com.phoenikx.communityhelp.exceptions.AuthenticationException;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    private ExceptionBody getErrorBody(String message) {
        return ExceptionBody.builder().message(message).build();
    }

    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<ExceptionBody> handle400(InvalidRequestException ex, WebRequest request) {
        log.error("Caught exception", ex);
        return new ResponseEntity<>(getErrorBody("Invalid Request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ExceptionBody> handle500(InvalidRequestException ex, WebRequest request) {
        log.error("Caught exception", ex);
        return new ResponseEntity<>(getErrorBody("Internal error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    protected ResponseEntity<ExceptionBody> handle401(InvalidRequestException ex, WebRequest request) {
        log.error("Caught exception", ex);
        return new ResponseEntity<>(getErrorBody("Invalid token."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
