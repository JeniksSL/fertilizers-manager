package com.iba.fertilizer_service.domain;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_prices", uniqueConstraints = {@UniqueConstraint(name = "user_id_product_id_type_unique", columnNames = {"user_id", "product_id", "type"})})

public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Basic
    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Basic
    @Column(name = "type", nullable = false)
    private String type;

}
