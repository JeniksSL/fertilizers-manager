package com.iba.fertilizer_service.client;


import com.iba.fertilizersmanager.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "calc-service", url = "http://localhost:8080/calc")
public interface CalcClient {

    @PostMapping("/calculations/{length}")
    CalculationResponseDto getProductsComposition(@PathVariable Integer length, CalculationRequestDto calculationRequest);
}
