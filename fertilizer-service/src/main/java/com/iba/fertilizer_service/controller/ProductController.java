package com.iba.fertilizer_service.controller;


import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizer_service.dto.ProductDto;
import com.iba.fertilizer_service.facade.ProductFacade;

import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.dto.ProductCompositionDto;
import com.iba.fertilizersmanager.utils.ControllerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductFacade productFacade;

    @GetMapping
    PageDto<ProductDto> getAllByCriteria(@RequestParam(defaultValue = "0", required = false) Integer page,
                                         @RequestParam(defaultValue = "10", required = false) Integer size,
                                         ProductCriteriaDTO criteria) {
        return productFacade.getAllByCriteria(criteria, page, size);
    }
    @GetMapping("/compositions")
    List<ProductCompositionDto> getAllCompositionsById(List<Long> productIds){
        return List.of(new ProductCompositionDto(1L), new ProductCompositionDto(2L)); //productFacade.getAllCompositionsById(productIds);
    }

    @GetMapping("/{id}")
    ProductDto findById(@PathVariable("id") Long productId) {
        return productFacade.getById(productId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    ProductDto create(@Valid @RequestBody ProductDto productDto,
                                    final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
       return productFacade.create(productDto);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    ProductDto update(@Valid @RequestBody ProductDto productDto,
                                      @PathVariable("id") Long productId,
                                      final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return productFacade.update(productDto,productId);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long productId) {
        productFacade.deleteById(productId);
    }

}