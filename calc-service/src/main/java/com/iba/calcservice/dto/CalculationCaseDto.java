package com.iba.calcservice.dto;

import com.iba.fertilizers.commons.dto.SubstanceCompact;
import com.iba.fertilizers.commons.dto.core.ProductPriceRateDto;

import java.util.List;

public class CalculationCaseDto {
    private String priceType;
    private String totalPrice;
    private List<SubstanceCompact> substances;
    private List<ProductPriceRateDto> products;
}
