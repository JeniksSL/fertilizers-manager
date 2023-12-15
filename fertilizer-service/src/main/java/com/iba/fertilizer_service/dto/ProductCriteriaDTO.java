package com.iba.fertilizer_service.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class ProductCriteriaDTO {
    private String name;
    private String description;
    private Set<Long> substances;
    private Boolean isCommon;
}
