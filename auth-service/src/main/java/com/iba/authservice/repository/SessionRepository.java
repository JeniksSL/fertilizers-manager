package com.iba.authservice.repository;


import com.iba.authservice.domain.Session;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SessionRepository extends BaseRepository<Session, UUID> {
}
