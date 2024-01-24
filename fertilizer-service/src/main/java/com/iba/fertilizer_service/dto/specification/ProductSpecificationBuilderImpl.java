package com.iba.fertilizer_service.dto.specification;

import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductSpecificationBuilderImpl implements ProductSpecificationBuilder {

    private Specification<Product> getByNameLike(String name) {
        if (name==null) return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private Specification<Product> getByDescriptionLike(String description) {
        if (description==null) return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + description+ "%");
    }

    private Specification<Product> getAllBySubstancesEqual(Set<Long> substanceIds) {
        if (substanceIds==null||substanceIds.size()==0)
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .and(root.joinMap("substanceMap").key().get("id").in(substanceIds));
    }

    private Specification<Product> getByUser(Long userId, Boolean isCommon) {
        if (isCommon!=null&&isCommon) return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .or(criteriaBuilder.equal(root.get("userId"), userId ), criteriaBuilder.isTrue(root.get("isCommon")));
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }

    public Specification<Product> build(ProductCriteriaDTO criteria, Long userId) {
        return Specification.where(getByNameLike(criteria.getName())
                .and(getAllBySubstancesEqual(criteria.getSubstances()))
                .and(getByDescriptionLike(criteria.getDescription()))
                .and(getByUser(userId, criteria.getIsCommon())));
    }
    public Specification<Product> build(ProductCriteriaDTO criteria){
        return Specification.where(getByNameLike(criteria.getName())
                .and(getAllBySubstancesEqual(criteria.getSubstances()))
                .and(getByDescriptionLike(criteria.getDescription())));
    }
}
