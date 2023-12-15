package com.iba.fertilizer_service.facade.impl;

import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizer_service.dto.ProductDto;

import com.iba.fertilizer_service.facade.AdminProductFacade;
import com.iba.fertilizer_service.service.AdminProductService;

import com.iba.fertilizer_service.service.impl.UserService;

import com.iba.fertilizersmanager.DomainConst;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.core.mapper.DoubleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminProductFacadeImpl implements AdminProductFacade {

    private final AdminProductService adminProductService;
    private final UserService userService;
    private final DoubleMapper<Product, ProductDto> doubleMapper;

    @Override
    public PageDto<ProductDto> getAllByCriteria(ProductCriteriaDTO criteria, Integer page, Integer size) {
        final Page<Product> productPage = criteria == null?adminProductService.getAll(page, size):adminProductService.getAllByCriteria(criteria, page, size);
        return new PageDto<>(productPage.stream().map(doubleMapper::toDto).toList(),
                productPage.getTotalPages(),
                productPage.getTotalElements());
    }
    @Override
    public ProductDto getById(Long productId) {
        return doubleMapper.toDto(adminProductService.getById(productId).orElseThrow());//TODO
    }

    @Override
    public ProductDto createCommon(ProductDto productDto) {
        if (productDto.getIsCommon()){
            Product newProduct = doubleMapper.toEntity(productDto);
            newProduct.setUserId(DomainConst.COMMON_PRODUCT_USER);
            return doubleMapper.toDto(adminProductService.create(newProduct));
        }
        throw new RuntimeException("Can't create custom product");
    }

    @Override
    public ProductDto updateCommon(ProductDto productDto, Long productId) {
        Product product = adminProductService.getById(productId).orElseThrow();//TODO
        if (product.isCommon()){
            Product newProduct = doubleMapper.toEntity(productDto);
            newProduct.setUserId(product.getUserId());
            newProduct.setId(productId);
            return doubleMapper.toDto(adminProductService.update(newProduct, productId));
        }
        throw new RuntimeException("Can't update custom product");
    }

    @Override
    public void deleteCommonById(Long productId) {
        if (adminProductService.getById(productId).orElseThrow().isCommon())//TODO
            adminProductService.deleteById(productId);
        throw new RuntimeException("Can't update custom product");
    }


}
