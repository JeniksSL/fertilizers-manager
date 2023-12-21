package com.iba.fertilizer_service.domain;


import com.iba.fertilizersmanager.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "substances")
public class Substance implements BaseEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "color", nullable = false)
    private Integer color;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "image_url")
    private String image;


}