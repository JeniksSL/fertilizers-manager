package com.iba.fertilizer_service.service;


import com.iba.fertilizer_service.domain.Substance;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SubstanceService {

    Page<Substance> getAllByCriteria(String criteria, Integer page, Integer size);

    Optional<Substance> getById(Long id);

    Optional<Substance> getByName(String name);

    Substance create(Substance substance);
}
