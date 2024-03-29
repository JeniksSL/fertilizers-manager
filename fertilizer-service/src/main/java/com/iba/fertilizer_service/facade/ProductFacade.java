package com.iba.fertilizer_service.facade;


import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizersmanager.dto.ProductDto;
import com.iba.fertilizersmanager.dto.*;

public interface ProductFacade{
    PageDto<ProductDto> getAllByCriteria(ProductCriteriaDTO criteria, Integer page, Integer size);

    ProductDto getById(Long productId);

    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto, Long productId);

    void deleteById(Long productId);

    CalculationResponseDto resolveCase(CalculationRequestDto calculationRequest);

}
