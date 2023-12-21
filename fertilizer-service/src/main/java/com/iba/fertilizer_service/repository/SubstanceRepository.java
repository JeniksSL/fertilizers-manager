package com.iba.fertilizer_service.repository;


import com.iba.fertilizer_service.domain.Substance;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubstanceRepository extends BaseRepository<Substance, Long>{
    Optional<Substance> findByName(String name);

}
