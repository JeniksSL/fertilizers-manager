package com.iba.fertilizersmanager.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculationResponseDto {
    private Long id;
    private UnitsType unitsType;
    private List<CalculationCaseDto> cases;
}
