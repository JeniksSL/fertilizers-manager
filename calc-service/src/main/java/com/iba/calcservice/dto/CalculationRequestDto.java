package com.iba.calcservice.dto;

import com.iba.fertilizers.commons.dto.SubstanceCompact;

import java.util.List;

public record CalculationRequestDto (
    String priceType, List<SubstanceCompact> substances, List<ProductPriceDto> products){}
