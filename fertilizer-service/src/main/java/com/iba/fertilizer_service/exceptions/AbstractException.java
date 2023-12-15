package com.iba.fertilizer_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AbstractException extends RuntimeException {

    private final Integer httpCode;

    private final String error;


}
