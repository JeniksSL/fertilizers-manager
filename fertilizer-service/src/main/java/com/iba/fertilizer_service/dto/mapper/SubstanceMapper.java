package com.iba.fertilizer_service.dto.mapper;


import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.SubstanceDto;
import com.iba.fertilizer_service.dto.mapper.core.ColorConverter;
import com.iba.fertilizersmanager.dto.core.mapper.AbstractDoubleMapper;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SubstanceMapper extends AbstractDoubleMapper<Substance, SubstanceDto> {

    public SubstanceMapper(ModelMapper modelMapper) {
        super(Substance.class, SubstanceDto.class, modelMapper);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Substance.class, SubstanceDto.class)
                .addMappings(m -> m.skip(SubstanceDto::setColor))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(SubstanceDto.class, Substance.class)
                .addMappings(m -> m.skip(Substance::setColor))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Substance source, SubstanceDto destination) {
        destination.setColor(ColorConverter.getColorString(source));
    }

    @Override
    protected void mapSpecificFields(SubstanceDto source, Substance destination) {
        destination.setColor(ColorConverter.getColorInt(source));
    }
}
