package com.iba.fertilizer_service.exceptions;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class ErrorMessage implements Serializable {


    private Integer httpCode;

    private String error;

}
