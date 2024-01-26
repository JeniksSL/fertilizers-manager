package com.iba.fertilizer_service.domain;

import com.iba.fertilizersmanager.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "product_cases")
public class ProductCase implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "crop_name", nullable = false, length = 25)
    private String cropName;

    @Basic
    @Column(name = "field_name", nullable = false, length = 50)
    private String fieldName;

    @Basic
    @Column(name = "area")
    private BigDecimal area;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "case_nutrients", joinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "substance_id", referencedColumnName = "id")
    @Column(table ="case_nutrients", name = "rate", nullable = false)
    private Map<Substance, BigDecimal> substanceMap;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "case_products", joinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "product_id", referencedColumnName = "id")
    @Column(table ="case_products", name = "rate", nullable = false)
    private Map<Product, BigDecimal> productMap;
}
