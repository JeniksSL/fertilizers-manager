package com.iba.fertilizersmanager.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Builder
public record CalculationCaseDto(BigDecimal totalPrice, List<SubstanceCompact> substances, List<ProductCompactDto> products){
}
