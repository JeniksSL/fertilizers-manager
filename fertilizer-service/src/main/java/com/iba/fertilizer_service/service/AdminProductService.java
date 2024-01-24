package com.iba.fertilizer_service.service;

import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AdminProductService {

    Page<Product> getAllByCriteria(ProductCriteriaDTO criteria, Integer page, Integer size);

    Page<Product> getAll(Integer page, Integer size);

    Optional<Product> getById(Long productId);

    Product create(Product toEntity);

    Product update(Product toEntity, Long productId);

    void deleteById(Long productId);

}
