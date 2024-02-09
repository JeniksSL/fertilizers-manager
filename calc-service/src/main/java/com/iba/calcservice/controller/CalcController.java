package com.iba.calcservice.controller;



import com.iba.fertilizersmanager.dto.CalculationRequestDto;
import com.iba.fertilizersmanager.dto.CalculationResponseDto;
import com.iba.calcservice.facade.CalcFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("calc")
@RequiredArgsConstructor
public class CalcController {

    private final CalcFacade calcFacade;



    @PostMapping("/calculations/{length}")
    CalculationResponseDto calculate(@PathVariable(required = false) Integer length, @RequestBody CalculationRequestDto calculationRequestDto) {

        return calcFacade.calculate(calculationRequestDto, length);
    }

}
