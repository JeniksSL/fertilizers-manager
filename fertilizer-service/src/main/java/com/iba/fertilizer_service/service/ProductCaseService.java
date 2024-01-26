package com.iba.fertilizer_service.service;

import com.iba.fertilizer_service.domain.ProductCase;
import com.iba.fertilizer_service.dto.ProductCaseDto;
import org.springframework.data.domain.Page;

public interface ProductCaseService {
    Page<ProductCase> findAllForUser(Integer page, Integer size, Long currentUserId);

    ProductCase createForUser(ProductCase productCase, Long currentUserId);
}
