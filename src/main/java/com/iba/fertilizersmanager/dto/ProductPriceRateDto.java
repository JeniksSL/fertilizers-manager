package com.iba.fertilizersmanager.dto;



import java.math.BigDecimal;

public record ProductPriceRateDto (
    Long id,
   BigDecimal price,
    BigDecimal rate){
}
