package com.iba.calcservice.controller;


import com.iba.calcservice.client.ProductClient;
import com.iba.calcservice.dto.CalculationRequestDto;
import com.iba.calcservice.dto.CalculationResponseDto;
import com.iba.calcservice.facade.CalcFacade;
import com.iba.fertilizersmanager.dto.ProductCompositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/calculations")
@RequiredArgsConstructor
public class CalcController {

    private final CalcFacade calcFacade;
    private final ProductClient productClient;



    @PostMapping
    CalculationResponseDto calculate(@RequestBody CalculationRequestDto calculationRequestDto) {

        return calcFacade.calculate(calculationRequestDto);
    }

    @GetMapping
    List<ProductCompositionDto> getProductsComposition(){
        return productClient.getProductsComposition(List.of(1L, 2L));
    }



}
