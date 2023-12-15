package com.iba.fertilizer_service.facade;


import com.iba.fertilizer_service.dto.SubstanceDto;
import com.iba.fertilizersmanager.dto.PageDto;


public interface SubstanceFacade {

    PageDto<SubstanceDto> getAllByCriteria(String criteria, Integer page, Integer size);

    SubstanceDto findById(Long substanceId);


    SubstanceDto create(SubstanceDto substanceDto);


    SubstanceDto update(SubstanceDto substanceDto, Long substanceId);


    void deleteById(Long substanceId);


}
