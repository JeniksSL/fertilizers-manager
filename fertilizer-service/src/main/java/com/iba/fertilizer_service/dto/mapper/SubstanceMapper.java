package com.iba.fertilizer_service.dto.mapper;

import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.SubstanceDto;
import com.iba.fertilizersmanager.dto.core.mapper.AbstractDoubleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SubstanceMapper extends AbstractDoubleMapper<Substance, SubstanceDto> {

    public SubstanceMapper(ModelMapper modelMapper) {
        super(Substance.class, SubstanceDto.class, modelMapper);
    }






}
