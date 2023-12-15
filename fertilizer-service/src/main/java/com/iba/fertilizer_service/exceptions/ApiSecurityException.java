package com.iba.fertilizer_service.exceptions;

public class ApiSecurityException extends AbstractException{

    public ApiSecurityException(Integer httpCode, String error) {
        super(httpCode, error);
    }
}
