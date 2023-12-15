package com.iba.fertilizer_service.service;


import com.iba.fertilizersmanager.core.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


public interface BaseService<E extends BaseEntity<ID>, ID> {

    Optional<E> getById(ID id);
    Page<E> getAll(Integer page, Integer size);

    Page<E> getAllByCriteria(Specification<E> specification, Integer page, Integer size);

    E create(E obj);

    void deleteById(ID id);

    E update(E obj, ID id);

    boolean existsById(ID id);

}
