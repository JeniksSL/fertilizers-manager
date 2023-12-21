package com.iba.fertilizersmanager.dto;


import lombok.Builder;

import java.util.List;

@Builder
public record CalculationRequestDto (
        String priceType, List<SubstanceCompact> substances, List<ProductCompactDto> products){}
