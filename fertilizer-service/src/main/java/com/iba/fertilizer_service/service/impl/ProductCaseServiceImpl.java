package com.iba.fertilizer_service.service.impl;


import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.domain.ProductCase;
import com.iba.fertilizer_service.dto.ProductCaseDto;
import com.iba.fertilizer_service.repository.BaseRepository;
import com.iba.fertilizer_service.repository.ProductCaseRepository;
import com.iba.fertilizer_service.service.AbstractService;
import com.iba.fertilizer_service.service.ProductCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ProductCaseServiceImpl extends AbstractService<ProductCase, Long> implements ProductCaseService {

    private final ProductCaseRepository productCaseRepository;

    public ProductCaseServiceImpl(ProductCaseRepository productCaseRepository) {
        super(productCaseRepository);
        this.productCaseRepository = productCaseRepository;
    }

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

    @Override
    public Optional<ProductCase> getByIdAndUserId(Long caseId, Long currentUserId) {
        return productCaseRepository.findByIdAndUserId(caseId, currentUserId);
    }

    @Override
    public ProductCase updateForUser(ProductCase productCase, Long currentUserId) {
        ProductCase original = productCaseRepository.findByIdAndUserId(productCase.getId(), currentUserId).orElseThrow();
        updateProductCase(original, productCase);
        return productCaseRepository.save(original);
    }

    private void updateProductCase(ProductCase original, ProductCase productCase) {
        original.setCropName(productCase.getCropName());
        original.setFieldName(productCase.getFieldName());
        original.setArea(productCase.getArea());
    }


}
