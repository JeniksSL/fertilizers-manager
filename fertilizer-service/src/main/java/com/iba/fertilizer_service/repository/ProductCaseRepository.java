package com.iba.fertilizer_service.repository;

import com.iba.fertilizer_service.domain.ProductCase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCaseRepository extends BaseRepository<ProductCase, Long>{
    Optional<ProductCase> findByIdAndUserId(Long caseId, Long currentUserId);

}
