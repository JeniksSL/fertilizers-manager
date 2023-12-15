package com.iba.fertilizer_service.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaSpecificationExecutor<T>, JpaRepository<T, ID> {



}
