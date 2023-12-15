package com.iba.fertilizer_service.facade;

import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizer_service.dto.ProductDto;
import com.iba.fertilizersmanager.dto.PageDto;


public interface AdminProductFacade {
    PageDto<ProductDto> getAllByCriteria(ProductCriteriaDTO criteria, Integer page, Integer size);
    ProductDto getById(Long productId);

    ProductDto createCommon(ProductDto productDto);

    ProductDto updateCommon(ProductDto productDto, Long productId);

    void deleteCommonById(Long productId);

}

