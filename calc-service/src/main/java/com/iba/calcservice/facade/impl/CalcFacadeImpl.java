package com.iba.calcservice.facade.impl;

import com.iba.calcservice.client.ProductClient;
import com.iba.calcservice.dto.*;
import com.iba.calcservice.facade.CalcFacade;
import com.iba.fertilizersmanager.dto.ProductCompositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalcFacadeImpl implements CalcFacade {

    private final ProductClient productClient;

    @Override
    public CalculationResponseDto calculate(CalculationRequestDto calculationRequestDto) {
        List<ProductCompositionDto> compositionDtos= productClient.getProductsComposition(calculationRequestDto.products().stream().map(ProductPriceDto::id).toList());





        return null;
    }
}
