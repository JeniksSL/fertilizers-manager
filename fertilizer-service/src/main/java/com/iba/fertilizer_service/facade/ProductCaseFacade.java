package com.iba.fertilizer_service.facade;

import com.iba.fertilizer_service.dto.ProductCaseDto;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.ProductDto;

public interface ProductCaseFacade {
    PageDto<ProductCaseDto> findAll(Integer page, Integer size);

    ProductCaseDto create(ProductCaseDto productCaseDto);

    void deleteById(Long caseId);

}
