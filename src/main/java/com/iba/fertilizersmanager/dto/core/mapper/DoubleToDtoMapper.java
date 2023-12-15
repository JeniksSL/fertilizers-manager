package com.iba.fertilizersmanager.dto.core.mapper;


import com.iba.fertilizersmanager.core.BaseEntity;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import com.iba.fertilizersmanager.dto.core.CompactDto;

public interface DoubleToDtoMapper <E extends BaseEntity<?>, D extends BaseDto, C extends CompactDto> extends DoubleMapper<E, D>{
    public C toCompactFromEntity(E entity);
}
