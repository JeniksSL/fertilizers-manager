package com.iba.fertilizer_service.service.impl;



import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizer_service.dto.specification.ProductSpecificationBuilder;
import com.iba.fertilizer_service.repository.ProductRepository;
import com.iba.fertilizer_service.service.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends AbstractService<Product, Long> implements ProductService, AdminProductService {

    public ProductServiceImpl(ProductRepository productRepository, ProductSpecificationBuilder ProductSpecificationBuilder, ProductSpecificationBuilder specificationBuilder) {
        super(productRepository);
        this.productRepository=productRepository;
        this.specificationBuilder = ProductSpecificationBuilder;
    }

    private final ProductSpecificationBuilder specificationBuilder;
    private final ProductRepository productRepository;

    @Override
    public Page<Product> getAllByCriteriaAndUser(ProductCriteriaDTO criteria, Integer page, Integer size, Long currentUserId) {
        return super.getAllByCriteria(specificationBuilder.build(criteria, currentUserId), page, size);
    }

    @Override
    public Product createForUser(Product product, Long currentUserId) {
        product.setId(null);
        product.setUserId(currentUserId);
        return super.create(product);
    }

    @Override
    public Page<Product> getAllByCriteria(ProductCriteriaDTO criteria, Integer page, Integer size) {
        return super.getAllByCriteria(specificationBuilder.build(criteria), page, size);
    }


    @Override
    public Optional<Product> getByIdAndUserIdOrCommon(Long productId, Long currentUserId) {
        return productRepository.getByIdAndUserId(productId, currentUserId).or(()->productRepository.getByIdAndIsCommonTrue(productId));
    }

    @Override
    public Product updateForUser(Product product, Long productId, Long userId) {
        productRepository.getByIdAndUserId(productId, userId).orElseThrow();
        product.setId(productId);
        product.setUserId(userId);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getByIdAndUser(Long productId, Long currentUserId) {
        return productRepository.getByIdAndUserId(productId, currentUserId);
    }

    @Override
    public List<Product> getAllByIdAndUserIdOrCommon(List<Long> productIds, Long currentUserId) {
        return productRepository.findAllByIdInAndUserIdOrIsCommonTrue(productIds, currentUserId);
    }


}
