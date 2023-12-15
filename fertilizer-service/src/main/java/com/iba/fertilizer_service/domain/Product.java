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
@Table(name = "products")
public class Product implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Basic
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Basic
    @Column(name = "image_url")
    private String image;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "is_common", nullable = false)
    private boolean isCommon;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "compositions", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "substance_id", referencedColumnName = "id")
    @Column(table ="compositions", name = "content", nullable = false)
    private Map<Substance, BigDecimal> substanceMap;


}