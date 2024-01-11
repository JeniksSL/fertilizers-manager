package com.iba.fertilizer_service.facade.impl;


import com.iba.fertilizer_service.client.CalcClient;
import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizersmanager.dto.ProductDto;

import com.iba.fertilizer_service.facade.ProductFacade;
import com.iba.fertilizer_service.service.*;

import com.iba.fertilizer_service.service.impl.UserService;

import com.iba.fertilizersmanager.dto.*;
import com.iba.fertilizersmanager.dto.core.mapper.DoubleToDtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final UserService userService;
    private final DoubleToDtoMapper<Product, ProductDto, ProductCompactDto> doubleMapper;

    private final CalcClient calcClient;

    @Override
    public PageDto<ProductDto> getAllByCriteria(ProductCriteriaDTO criteria, Integer page, Integer size) {
        final Page<Product> productPage = productService.getAllByCriteriaAndUser(criteria, page, size, userService.getCurrentUserId());
        return new PageDto<>(productPage.stream().map(doubleMapper::toDto).toList(),
                productPage.getTotalPages(),
                productPage.getTotalElements());
    }

    @Override
    public ProductDto getById(Long productId) {
        return doubleMapper.toDto(productService.getByIdAndUserIdOrCommon(productId, userService.getCurrentUserId()).orElseThrow());
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        if (productDto.getIsCommon()) throw new RuntimeException("Can't create common product");
        return doubleMapper.toDto(productService.createForUser(doubleMapper.toEntity(productDto), userService.getCurrentUserId()));
    }

    @Override
    public ProductDto update(ProductDto productDto, Long productId) {
        if (productDto.getIsCommon()) throw new RuntimeException("Can't set common product");
        Long userId = userService.getCurrentUserId();
        return doubleMapper.toDto(productService.updateForUser(doubleMapper.toEntity(productDto), productId, userId));
    }

    @Override
    public void deleteById(Long productId) {
        productService.getByIdAndUser(productId, userService.getCurrentUserId()).orElseThrow();//TODO
        productService.deleteById(productId);
    }

    @Override
    public CalculationResponseDto resolveCase(CalculationRequestDto calculationRequest) {
        validateAndSupplementRequest(calculationRequest);
        return calcClient.getProductsComposition(calculationRequest);
    }

    @Override
    public CalculationResponseDto resolveCase() {
        Page<Product> pageDto = productService.getAllByCriteriaAndUser(new ProductCriteriaDTO(null,null,null,true), 0, 10, 0L);

        CalculationRequestDto calculationRequest = CalculationRequestDto.builder()
                .products( pageDto.get().map(doubleMapper::toCompactFromEntity).map(productCompactDto -> {
                    productCompactDto.setPrice(BigDecimal.TEN);
                    return productCompactDto;
                }).toList())
                .substances(List.of(new SubstanceCompact(1L, BigDecimal.valueOf(200)), new SubstanceCompact(2L, BigDecimal.valueOf(150))))
                .build();
        return calcClient.getProductsComposition(calculationRequest);
    }

    @Transactional
    private void validateAndSupplementRequest(CalculationRequestDto calculationRequest) {
        Long currentUserId = userService.getCurrentUserId();
        calculationRequest.products()
                .forEach(productCompactDto -> {
            ProductCompactDto original = doubleMapper.toCompactFromEntity(productService.getByIdAndUserIdOrCommon(productCompactDto.getId(), currentUserId).orElseThrow());
            productCompactDto.setSubstanceSet(original.getSubstanceSet());
        });
    }


    private List<ProductCompactDto> getAllCompositionsById(List<Long> productIds) {
        return productService.getAllByIdAndUserIdOrCommon(productIds, userService.getCurrentUserId())
                .stream()
                .map(doubleMapper::toCompactFromEntity)
                .toList();
    }
}
