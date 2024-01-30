package com.iba.fertilizer_service.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
public class ProductPriceRate {

    BigDecimal price;
    BigDecimal rate;
}
