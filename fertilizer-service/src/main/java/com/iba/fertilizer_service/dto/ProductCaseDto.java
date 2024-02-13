package com.iba.fertilizer_service.dto;


import com.iba.fertilizersmanager.dto.*;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCaseDto extends BaseDto {

    private Long id;

    private String cropName;

    private String fieldName;

    private BigDecimal area;

    private UnitsType unitsType;

    private List<SubstanceCompact> substances;

    private List<ProductCompactDto> products;
}
