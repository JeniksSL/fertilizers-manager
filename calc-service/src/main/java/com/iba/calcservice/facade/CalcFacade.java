package com.iba.calcservice.facade;

import com.iba.calcservice.dto.CalculationRequestDto;
import com.iba.calcservice.dto.CalculationResponseDto;

public interface CalcFacade {
    CalculationResponseDto calculate(CalculationRequestDto calculationRequestDto);
}
