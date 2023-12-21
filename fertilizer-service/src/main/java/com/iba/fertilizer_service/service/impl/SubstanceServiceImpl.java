package com.iba.fertilizer_service.service.impl;

import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.specification.SubstanceSpecificationBuilder;
import com.iba.fertilizer_service.repository.SubstanceRepository;
import com.iba.fertilizer_service.service.AbstractService;
import com.iba.fertilizer_service.service.SubstanceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubstanceServiceImpl extends AbstractService<Substance, Long> implements SubstanceService {

    private final SubstanceRepository substanceRepository;
    private final SubstanceSpecificationBuilder specificationBuilder;

    public SubstanceServiceImpl(SubstanceRepository baseRepository, SubstanceSpecificationBuilder specificationBuilder) {
        super(baseRepository);
        this.specificationBuilder = specificationBuilder;
        this.substanceRepository = baseRepository;
    }
    @Override
    public Page<Substance> getAllByCriteria(String criteria, Integer page, Integer size) {
        return super.getAllByCriteria(specificationBuilder.build(criteria), page, size);
    }

    @Override
    public Optional<Substance> getByName(String name) {
        return substanceRepository.findByName(name);
    }
}
