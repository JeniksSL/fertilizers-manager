package com.iba.calcservice.facade;

import com.iba.fertilizersmanager.dto.*;

public interface CalcFacade {
    CalculationResponseDto calculate(CalculationRequestDto calculationRequestDto, Integer length);
}
