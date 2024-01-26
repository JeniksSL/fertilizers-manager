package com.iba.fertilizer_service.controller;

import com.iba.fertilizer_service.dto.ProductCaseDto;
import com.iba.fertilizer_service.facade.ProductCaseFacade;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.ProductDto;
import com.iba.fertilizersmanager.utils.ControllerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("fertilizers/cases")
@RequiredArgsConstructor
public class ProductCaseController {

    private final ProductCaseFacade productCaseFacade;


    @GetMapping
    PageDto<ProductCaseDto> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                    @RequestParam(defaultValue = "10", required = false) Integer size) {
        return productCaseFacade.findAll(page, size);
    }


    @PostMapping
    ProductCaseDto create(@Valid @RequestBody ProductCaseDto productCaseDto,
                      final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return productCaseFacade.create(productCaseDto);
    }

}
