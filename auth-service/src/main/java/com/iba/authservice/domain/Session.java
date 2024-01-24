package com.iba.authservice.domain;


import com.iba.authservice.domain.converter.RoleToListStringConverter;
import com.iba.fertilizersmanager.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "user_sessions"
)
@Getter
@Setter

@NoArgsConstructor
public class Session extends Belong2User  {
    @Builder
    public Session(Long userId, UUID id, String refreshToken, String accessToken, String fingerPrint, Set<RoleType> roles) {
        super(userId);
        this.id = id;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.fingerPrint = fingerPrint;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_id", nullable = false)
    private UUID id;

    @Column(name = "refresh_token", unique = true)
    private String refreshToken;

    @Column(name = "access_token", unique = true)
    private String accessToken;

    @Column(name = "finger_print")
    private String fingerPrint;

    @Column(name = "tags")
    @Convert(converter = RoleToListStringConverter.class)
    private Set<RoleType> roles;

}
