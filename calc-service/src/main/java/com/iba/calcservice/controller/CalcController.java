package com.iba.calcservice.controller;



import com.iba.fertilizersmanager.dto.CalculationRequestDto;
import com.iba.fertilizersmanager.dto.CalculationResponseDto;
import com.iba.calcservice.facade.CalcFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/calculations")
@RequiredArgsConstructor
public class CalcController {

    private final CalcFacade calcFacade;



    @PostMapping
    CalculationResponseDto calculate(@RequestBody CalculationRequestDto calculationRequestDto) {

        return calcFacade.calculate(calculationRequestDto);
    }

}
