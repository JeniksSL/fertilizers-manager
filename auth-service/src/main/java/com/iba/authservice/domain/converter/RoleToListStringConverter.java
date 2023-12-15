package com.iba.authservice.domain.converter;


import com.iba.fertilizersmanager.RoleType;
import jakarta.persistence.AttributeConverter;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RoleToListStringConverter implements AttributeConverter<Set<RoleType>, String> {

    private static final String delimiter = ";";

    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        if (CollectionUtils.isEmpty(attribute)) return null;
        return attribute.stream().map(RoleType::name).collect(Collectors.joining(delimiter));
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {
        if (dbData == null) return new TreeSet<>();
        return Arrays
                .stream(dbData.split(delimiter))
                .map(RoleType::valueOf)
                .collect(Collectors.toSet());
    }




}
