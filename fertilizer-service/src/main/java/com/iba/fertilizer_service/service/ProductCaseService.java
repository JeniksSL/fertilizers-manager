package com.iba.fertilizer_service.service;

import com.iba.fertilizer_service.domain.ProductCase;
import com.iba.fertilizer_service.dto.ProductCaseDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductCaseService {
    Page<ProductCase> findAllForUser(Integer page, Integer size, Long currentUserId);

    ProductCase createForUser(ProductCase productCase, Long currentUserId);

    void deleteById(Long caseId);

    Optional<ProductCase> getByIdAndUserId(Long caseId, Long currentUserId);

    ProductCase updateForUser(ProductCase productCase, Long currentUserId);
}
