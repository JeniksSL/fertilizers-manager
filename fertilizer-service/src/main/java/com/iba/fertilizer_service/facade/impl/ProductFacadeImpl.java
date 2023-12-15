package com.iba.fertilizer_service.facade.impl;


import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizer_service.dto.ProductDto;

import com.iba.fertilizer_service.facade.ProductFacade;
import com.iba.fertilizer_service.service.*;

import com.iba.fertilizer_service.service.impl.UserService;

import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.ProductCompositionDto;
import com.iba.fertilizersmanager.dto.core.mapper.DoubleToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final UserService userService;
    private final DoubleToDtoMapper<Product, ProductDto, ProductCompositionDto> doubleMapper;

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
    public List<ProductCompositionDto> getAllCompositionsById(List<Long> productIds) {
        return productService.getAllByIdAndUserIdOrCommon(productIds, userService.getCurrentUserId())
                .stream()
                .map(doubleMapper::toCompactFromEntity)
                .toList();
    }
}
