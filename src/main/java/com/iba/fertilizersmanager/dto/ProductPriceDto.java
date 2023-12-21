package com.iba.fertilizersmanager.dto;



import java.math.BigDecimal;


public record ProductPriceDto (
    Long id,BigDecimal price){}
