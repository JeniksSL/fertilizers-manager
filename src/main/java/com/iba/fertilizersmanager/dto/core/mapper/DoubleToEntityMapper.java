package com.iba.fertilizersmanager.dto.core.mapper;


import com.iba.fertilizersmanager.core.BaseEntity;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import com.iba.fertilizersmanager.dto.core.CompactDto;

public interface DoubleToEntityMapper<E extends BaseEntity<?>, D extends BaseDto, C extends CompactDto> extends DoubleMapper<E, D> {
    E toEntityFromCompact(final C c);


}
