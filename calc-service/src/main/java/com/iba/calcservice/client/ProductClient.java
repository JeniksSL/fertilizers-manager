package com.iba.calcservice.client;



import com.iba.fertilizersmanager.dto.ProductCompositionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient(name = "fertilizers-service")
public interface ProductClient {

    @GetMapping("/compositions")
    List<ProductCompositionDto> getProductsComposition(List<Long> productIds);
}
