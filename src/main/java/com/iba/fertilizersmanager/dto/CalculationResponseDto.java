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
    private String priceType;
    private List<CalculationCaseDto> cases;
}
