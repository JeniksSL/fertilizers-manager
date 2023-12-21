package com.iba.fertilizer_service.client;


import com.iba.fertilizersmanager.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "calc-service", url = "http://localhost:8070")
public interface CalcClient {

    @PostMapping("/calculations")
    CalculationResponseDto getProductsComposition(CalculationRequestDto calculationRequest);
}
