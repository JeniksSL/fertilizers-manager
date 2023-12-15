package com.iba.fertilizersmanager.dto.core.mapper;


import com.iba.fertilizersmanager.core.BaseEntity;
import com.iba.fertilizersmanager.dto.core.BaseDto;

public interface DoubleMapper<E extends BaseEntity<?>, D extends BaseDto> {

    E toEntity(final D d);
    D toDto(final E e);
}
