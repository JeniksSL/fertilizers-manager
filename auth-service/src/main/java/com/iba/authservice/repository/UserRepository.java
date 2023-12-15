package com.iba.authservice.repository;


import com.iba.authservice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
