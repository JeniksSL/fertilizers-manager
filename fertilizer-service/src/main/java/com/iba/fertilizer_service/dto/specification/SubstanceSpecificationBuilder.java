package com.iba.fertilizer_service.dto.specification;

import com.iba.fertilizer_service.domain.Substance;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SubstanceSpecificationBuilder implements SpecificationBuilder<Substance, String>{
    @Override
    public Specification<Substance> build(String criteria) {
        if (criteria==null) return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
        return Specification
                .where((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                        .like(root.get("name"), "%" + criteria + "%"));
    }
}
