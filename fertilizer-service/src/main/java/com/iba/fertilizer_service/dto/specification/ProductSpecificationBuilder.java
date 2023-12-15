package com.iba.fertilizer_service.dto.specification;

import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.domain.ProductCriteria;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import org.springframework.data.jpa.domain.Specification;

public interface ProductSpecificationBuilder {
    Specification<Product> build(ProductCriteriaDTO criteria, Long userId);
    Specification<Product> build(ProductCriteriaDTO criteria);
}
