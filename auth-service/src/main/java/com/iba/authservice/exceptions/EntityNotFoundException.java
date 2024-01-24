package com.iba.authservice.exceptions;



import com.iba.fertilizersmanager.core.BaseEntity;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

    public final static String NOT_FOUND = "Not found";
    private Class<? extends BaseEntity<?>> aClass;

    public EntityNotFoundException(Class<? extends BaseEntity<?>> aClass) {
        super(NOT_FOUND);
        this.aClass = aClass;
    }
}
