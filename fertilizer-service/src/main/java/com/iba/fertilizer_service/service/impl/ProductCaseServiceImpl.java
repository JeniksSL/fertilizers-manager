package com.iba.fertilizer_service.service.impl;


import com.iba.fertilizer_service.domain.ProductCase;
import com.iba.fertilizer_service.dto.ProductCaseDto;
import com.iba.fertilizer_service.repository.ProductCaseRepository;
import com.iba.fertilizer_service.service.ProductCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCaseServiceImpl implements ProductCaseService {

    private final ProductCaseRepository productCaseRepository;
    @Override
    public Page<ProductCase> findAllForUser(Integer page, Integer size, Long currentUserId) {
        return productCaseRepository.findAll((root, criteriaQuery, criteriaBuilder)->criteriaBuilder.equal(root.get("userId"), currentUserId),PageRequest.of(page, size));
    }

    @Override
    public ProductCase createForUser(ProductCase productCase, Long currentUserId) {
        productCase.setId(null);
        productCase.setUserId(currentUserId);
        return productCaseRepository.save(productCase);
    }
}
