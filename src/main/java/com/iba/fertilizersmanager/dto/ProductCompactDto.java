package com.iba.fertilizersmanager.dto;


import com.iba.fertilizersmanager.dto.core.CompactDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCompactDto extends CompactDto {
    private Long id;
    private BigDecimal price;
    private BigDecimal rate;
    private Set<SubstanceCompact> substanceSet;





}
