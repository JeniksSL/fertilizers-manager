package com.iba.calcservice.dto;



import java.math.BigDecimal;


public record ProductPriceDto (
    Long id,BigDecimal price){}
