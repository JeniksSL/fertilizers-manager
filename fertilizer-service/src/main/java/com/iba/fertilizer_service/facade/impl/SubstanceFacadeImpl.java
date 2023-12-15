package com.iba.fertilizer_service.facade.impl;




import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.SubstanceDto;
import com.iba.fertilizer_service.facade.SubstanceFacade;
import com.iba.fertilizer_service.service.SubstanceService;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.core.mapper.DoubleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubstanceFacadeImpl implements SubstanceFacade {

    private final SubstanceService substanceService;
    private final DoubleMapper<Substance, SubstanceDto> doubleMapper;

    @Override
    public PageDto<SubstanceDto> getAllByCriteria(String criteria, Integer page, Integer size) {
        Page<Substance> substancePage = substanceService.getAllByCriteria(criteria, page, size);
        return new PageDto<>(substancePage.stream().map(doubleMapper::toDto).toList(),
                substancePage.getTotalPages(),
                substancePage.getTotalElements());
    }

    @Override
    public SubstanceDto findById(Long substanceId) {
        return null;
    }

    @Override
    public SubstanceDto create(SubstanceDto substanceDto) {
        return null;
    }

    @Override
    public SubstanceDto update(SubstanceDto substanceDto, Long substanceId) {
        return null;
    }

    @Override
    public void deleteById(Long substanceId) {

    }
}
