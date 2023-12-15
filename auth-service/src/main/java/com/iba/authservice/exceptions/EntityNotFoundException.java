package com.iba.authservice.exceptions;


import com.iba.authservice.domain.DomainClass;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

    public final static String NOT_FOUND = "Not found";
    private Class<? extends DomainClass> aClass;

    public EntityNotFoundException(Class<? extends DomainClass> aClass) {
        super(NOT_FOUND);
        this.aClass = aClass;
    }
}
