package com.iba.authservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "user_codes"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCode extends Belong2User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code_id", nullable = false)
    private UUID id;

    @Column(name = "is_sent", nullable = false)
    private boolean isSent;

}
