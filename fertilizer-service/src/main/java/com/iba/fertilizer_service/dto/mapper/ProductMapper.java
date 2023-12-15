package com.iba.fertilizer_service.dto.mapper;

import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductDto;
import com.iba.fertilizer_service.service.SubstanceService;
import com.iba.fertilizersmanager.dto.ProductCompositionDto;
import com.iba.fertilizersmanager.dto.SubstanceCompact;
import com.iba.fertilizersmanager.dto.core.mapper.AbstractDoubleToDtoMapper;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper extends AbstractDoubleToDtoMapper<Product, ProductDto, ProductCompositionDto> {

    public ProductMapper(SubstanceService substanceService,  ModelMapper modelMapper) {
        super(Product.class, ProductDto.class, ProductCompositionDto.class, modelMapper);
        this.substanceService = substanceService;
    }


    private final SubstanceService substanceService;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Product.class, ProductDto.class)
                .addMappings(m -> m.skip(ProductDto::setSubstanceSet))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProductDto.class, Product.class)
                .addMappings(m -> m.skip(Product::setSubstanceMap))
                .setPostConverter(toEntityConverter());
        mapper.createTypeMap(Product.class, ProductCompositionDto.class)
                .addMappings(m -> m.skip(ProductCompositionDto::setSubstanceSet))
                .setPostConverter(toCompactDtoConverter());
    }

    @Override
    protected void mapSpecificFields(final Product source, final ProductDto destination) {
        destination.setSubstanceSet(
                Optional.ofNullable(source.getSubstanceMap()).orElse(Collections.emptyMap())
                        .entrySet().stream()
                        .map((entry)-> new SubstanceCompact(entry.getKey().getId(), entry.getValue()))
                        .collect(Collectors.toSet()));
    }

    @Override
    protected void mapSpecificFields(final ProductDto source, final Product destination) {
        destination.setSubstanceMap(Optional.ofNullable(source.getSubstanceSet()).orElse(Collections.emptySet())
                .stream()
                .collect(Collectors.toMap(
                        substance->substanceService.getById(substance.getId()).orElseThrow(),
                        SubstanceCompact::getContent)
                )
        );
    }

    @Override
    protected void mapSpecificFields(Product source, ProductCompositionDto destination) {
        destination.setSubstanceSet(
                Optional.ofNullable(source.getSubstanceMap()).orElse(Collections.emptyMap())
                        .entrySet().stream()
                        .map((entry)-> new SubstanceCompact(entry.getKey().getId(), entry.getValue()))
                        .collect(Collectors.toSet()));
    }
}
