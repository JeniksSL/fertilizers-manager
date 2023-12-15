package com.iba.fertilizer_service.facade;


import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.core.BaseDto;

public interface BaseFacade<D extends BaseDto, ID, CR>{

    D findById(ID id);

    PageDto<D> findAll(Integer page, Integer size);

    PageDto<D>findAllByCriteria(CR criteria, Integer page, Integer size);

    D create(D obj);

    void deleteById(ID id);

    D update(D obj, ID id);


}
