package com.iba.fertilizer_service.service;



import com.iba.fertilizer_service.repository.BaseRepository;
import com.iba.fertilizersmanager.core.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


@RequiredArgsConstructor
public abstract class AbstractService <E extends BaseEntity<ID>, ID> implements BaseService<E, ID> {

   private final BaseRepository<E,ID> baseRepository;
    public Optional<E> getById(ID id){
       return baseRepository.findById(id);
    }

    public Page<E> getAll(Integer page, Integer size){
            Pageable pageable = PageRequest.of(page, size);
            return baseRepository.findAll(pageable);
    }
    public Page<E> getAllByCriteria(Specification<E> specification, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return baseRepository.findAll(specification,pageable);
    }

    public E create(E obj){
        return baseRepository.save(obj);
    }

    public void deleteById(ID id){
        baseRepository.deleteById(id);
    }
    public E update(E obj, ID id){
        obj.setId(id);
        return baseRepository.save(obj);
    }
    public boolean existsById(ID id){
        return baseRepository.existsById(id);
    }

}
