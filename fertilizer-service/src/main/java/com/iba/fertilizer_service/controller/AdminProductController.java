package com.iba.fertilizer_service.controller;

import com.iba.fertilizer_service.dto.ProductCriteriaDTO;
import com.iba.fertilizersmanager.dto.ProductDto;
import com.iba.fertilizer_service.facade.AdminProductFacade;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.utils.ControllerUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductFacade adminProductFacade;

    @GetMapping
    PageDto<ProductDto> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                               @RequestParam(defaultValue = "10", required = false) Integer size,
                               @NotNull ProductCriteriaDTO criteria) {
        return adminProductFacade.getAllByCriteria(criteria, page, size);
    }

    @GetMapping("/{id}")
    ProductDto getById(@PathVariable("id") Long productId) {
        return adminProductFacade.getById(productId);
    }


    @PostMapping
    ProductDto createCommon(@Valid @RequestBody ProductDto productDto,
                      final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return adminProductFacade.createCommon(productDto);
    }

    @PutMapping("/{id}")
    ProductDto updateCommon(@Valid @RequestBody ProductDto productDto,
                      @PathVariable("id") Long productId,
                      final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return adminProductFacade.updateCommon(productDto,productId);
    }

    @DeleteMapping("/{id}")
    void deleteCommon(@PathVariable("id") Long productId) {
        adminProductFacade.deleteCommonById(productId);
    }
}
