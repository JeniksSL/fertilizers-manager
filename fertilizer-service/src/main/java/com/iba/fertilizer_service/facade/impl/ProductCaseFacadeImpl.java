package com.iba.fertilizer_service.facade.impl;

import com.iba.fertilizer_service.domain.*;
import com.iba.fertilizer_service.dto.ProductCaseDto;
import com.iba.fertilizer_service.facade.ProductCaseFacade;
import com.iba.fertilizer_service.service.*;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.ProductDto;
import com.iba.fertilizersmanager.dto.core.mapper.DoubleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductCaseFacadeImpl implements ProductCaseFacade {


    private final ProductCaseService productCaseService;
    private final UserService userService;

    private final ProductService productService;

    private final SubstanceService substanceService;

    private final DoubleMapper<ProductCase, ProductCaseDto> doubleMapper;

    @Override
    public PageDto<ProductCaseDto> findAll(Integer page, Integer size) {
        final Page<ProductCase> productCasePage = productCaseService.findAllForUser(page, size, userService.getCurrentUserId());
        return new PageDto<>(productCasePage.stream().map(doubleMapper::toDto).toList(),
                productCasePage.getTotalPages(),
                productCasePage.getTotalElements());
    }

    @Override
    public ProductCaseDto create(ProductCaseDto productCaseDto) {
        Long currentUserId = userService.getCurrentUserId();
        ProductCase productCase = doubleMapper.toEntity(productCaseDto);
        Map<Substance, BigDecimal> substanceBigDecimalMap=new HashMap<>();
        Map<Product, ProductPriceRate> productBigDecimalMap=new HashMap<>();
        productCaseDto.getSubstances().forEach(it->substanceBigDecimalMap.put(substanceService.getById(it.getId()).orElseThrow(),it.getContent()));
        productCaseDto
                .getProducts()
                .forEach(it->productBigDecimalMap.put(
                        productService.getByIdAndUserIdOrCommon(it.getId(), currentUserId).orElseThrow(),
                        ProductPriceRate.builder().rate(it.getRate()).price(it.getPrice()).build()));
        productCase.setSubstanceMap(substanceBigDecimalMap);
        productCase.setProductMap(productBigDecimalMap);
        return doubleMapper.toDto(productCaseService.createForUser(productCase, currentUserId));
    }

    @Override
    public void deleteById(Long caseId) {
        productCaseService.getByIdAndUserId(caseId, userService.getCurrentUserId()).orElseThrow();
        productCaseService.deleteById(caseId);
    }

    @Override
    public ProductCaseDto update(ProductCaseDto productCaseDto) {
        ProductCase productCase = doubleMapper.toEntity(productCaseDto);
        return doubleMapper.toDto(productCaseService.updateForUser(productCase, userService.getCurrentUserId()));
    }
}
