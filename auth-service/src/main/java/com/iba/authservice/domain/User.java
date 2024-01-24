package com.iba.authservice.domain;


import com.iba.fertilizersmanager.RoleType;
import com.iba.fertilizersmanager.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(
        name = "users"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends TrackingEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "ava_image")
    private String image;

    @Column(name = "is_email_confirmed")
    private Boolean isEmailConfirmed;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles",
            joinColumns = @JoinColumn(name = "user_id"),
    uniqueConstraints =
            @UniqueConstraint(columnNames = {"role", "user_id"})
    )
    @Column(table = "roles", name = "role", nullable = false, length = 30)
    private Set<RoleType> roles;


}
