package com.phoenikx.communityhelp.config;

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
    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<String> handleConflict(InvalidRequestException ex, WebRequest request) {
        log.error("Caught exception", ex);
        return new ResponseEntity<>("Invalid input provided.", HttpStatus.BAD_REQUEST);
    }
}
