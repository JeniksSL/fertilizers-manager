package com.iba.fertilizer_service.service;


import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    Page<Product> getAllByCriteriaAndUser(ProductCriteriaDTO criteria, Integer page, Integer size, Long currentUserId);

    Optional<Product> getByIdAndUserIdOrCommon(Long productId, Long currentUserId);

    Product createForUser(Product toEntity, Long currentUserId);

    Product updateForUser(Product toEntity, Long productId, Long userId);

    void deleteById(Long productId);

    Optional<Product> getByIdAndUser(Long productId, Long currentUserId);

    List<Product> getAllByIdAndUserIdOrCommon(List<Long> productIds, Long currentUserId);
}
