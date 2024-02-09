package com.iba.calcservice.facade.impl;

import com.iba.calcservice.facade.CalcFacade;
import com.iba.calcservice.facade.RequestResolver;
import com.iba.calcservice.model.*;
import com.iba.fertilizersmanager.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalcFacadeImpl implements CalcFacade {

    private final RequestResolver requestResolver;


    @Override
    public CalculationResponseDto calculate(CalculationRequestDto calculationRequestDto, Integer length) {
        length=length!=null?length:1;
        return requestResolver.getResolveCases(calculationRequestDto, length);

    }

    private ProductCompactDto convert(LogicProduct logicProduct) {
        return new ProductCompactDto(logicProduct.getId(), null, BigDecimal.valueOf(logicProduct.getRate()), null);
    }
}
