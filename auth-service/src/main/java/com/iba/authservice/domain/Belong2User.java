package com.iba.authservice.domain;


import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@MappedSuperclass
public abstract class Belong2User {

    public Belong2User(Long userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Getter
    @Setter
    @Column(name = "user_id")
    private Long userId;

}
