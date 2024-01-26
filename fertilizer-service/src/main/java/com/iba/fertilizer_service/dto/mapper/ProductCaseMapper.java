package com.iba.fertilizer_service.dto.mapper;

import com.iba.fertilizer_service.domain.ProductCase;
import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.ProductCaseDto;
import com.iba.fertilizer_service.dto.SubstanceDto;
import com.iba.fertilizer_service.dto.mapper.core.ColorConverter;
import com.iba.fertilizersmanager.dto.ProductCompactDto;
import com.iba.fertilizersmanager.dto.SubstanceCompact;
import com.iba.fertilizersmanager.dto.core.mapper.AbstractDoubleMapper;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductCaseMapper extends AbstractDoubleMapper<ProductCase, ProductCaseDto> {
    public ProductCaseMapper(ModelMapper modelMapper) {
        super(ProductCase.class, ProductCaseDto.class, modelMapper);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ProductCase.class, ProductCaseDto.class)
                .addMappings(m -> m.skip(ProductCaseDto::setSubstances))
                .addMappings(m -> m.skip(ProductCaseDto::setProducts))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProductCaseDto.class, ProductCase.class)
                .addMappings(m -> m.skip(ProductCase::setSubstanceMap))
                .addMappings(m -> m.skip(ProductCase::setProductMap))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ProductCase source, ProductCaseDto destination) {
        destination.setSubstances(source
                .getSubstanceMap()
                .entrySet()
                .stream()
                .map(entry -> SubstanceCompact.builder().id(entry.getKey().getId()).content(entry.getValue()).build()).toList());
        destination.setProducts(source
                .getProductMap()
                .entrySet()
                .stream()
                .map(entry->ProductCompactDto.builder().id(entry.getKey().getId()).rate(entry.getValue()).build()).toList());
    }

    @Override
    protected void mapSpecificFields(ProductCaseDto source, ProductCase destination) {
        destination.setSubstanceMap(Map.of());
        destination.setProductMap(Map.of());
    }

}
