package com.iba.fertilizer_service.exceptions;


public class ServiceException extends AbstractException {
    public ServiceException(int value, String error) {
        super(value, error);
    }
}
