package com.iba.fertilizer_service.repository;


import com.iba.fertilizer_service.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    Optional<Product> getByIdAndUserId(Long productId, Long currentUserId);


    Optional<Product> getByIdAndIsCommonTrue(Long productId);


    @Query("select p from Product p where p.id in ?1 and (p.userId = ?2 or p.isCommon = true)")
    List<Product> findAllByIdInAndUserIdOrIsCommonTrue (List<Long> productIds, Long currentUserId);
}
