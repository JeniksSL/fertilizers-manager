package com.iba.authservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class TrackingEntity {

    @CreatedDate
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @LastModifiedDate
    @Column(name = "date_of_last_update")
    private LocalDateTime dateOfLastUpdate;

}
