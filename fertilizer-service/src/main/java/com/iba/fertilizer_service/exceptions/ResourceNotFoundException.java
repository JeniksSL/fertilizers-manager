package com.iba.fertilizer_service.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AbstractException {

    public ResourceNotFoundException(String error) {
        super(HttpStatus.NOT_FOUND.value(), error);
    }

}
