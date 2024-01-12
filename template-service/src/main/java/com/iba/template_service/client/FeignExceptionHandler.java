package com.iba.template_service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionHandler {
    @ExceptionHandler(value = Response302Exception.class)
    public ResponseEntity<?> handleResponseException(Response302Exception exception) {
        return ResponseEntity
                .status(exception.getResponse().status())
                .body(exception.getResponse().body().toString());
    }
}
