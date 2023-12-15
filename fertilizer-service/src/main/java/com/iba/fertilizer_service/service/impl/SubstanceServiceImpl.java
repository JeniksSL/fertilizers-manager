package com.iba.fertilizer_service.service.impl;

import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.specification.SubstanceSpecificationBuilder;
import com.iba.fertilizer_service.repository.SubstanceRepository;
import com.iba.fertilizer_service.service.AbstractService;
import com.iba.fertilizer_service.service.SubstanceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SubstanceServiceImpl extends AbstractService<Substance, Long> implements SubstanceService {

    public SubstanceServiceImpl(SubstanceRepository baseRepository, SubstanceSpecificationBuilder specificationBuilder) {
        super(baseRepository);
        this.specificationBuilder = specificationBuilder;
    }
    private final SubstanceSpecificationBuilder specificationBuilder;

    @Override
    public Page<Substance> getAllByCriteria(String criteria, Integer page, Integer size) {
        return super.getAllByCriteria(specificationBuilder.build(criteria), page, size);
    }
}
