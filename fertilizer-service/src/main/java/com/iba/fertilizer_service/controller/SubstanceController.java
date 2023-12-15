package com.iba.fertilizer_service.controller;

import com.iba.fertilizer_service.dto.SubstanceDto;
import com.iba.fertilizer_service.facade.SubstanceFacade;
import com.iba.fertilizersmanager.dto.PageDto;
import com.iba.fertilizersmanager.utils.ControllerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/substances")
@RequiredArgsConstructor
public class SubstanceController {
    private final SubstanceFacade substanceFacade;

    @GetMapping
    PageDto<SubstanceDto> findAllByCriteria(@RequestParam(defaultValue = "0", required = false) Integer page,
                                            @RequestParam(defaultValue = "10", required = false) Integer size,
                                            @RequestParam(required = false) String criteria) {
        return substanceFacade.getAllByCriteria(criteria, page, size);
    }

    @GetMapping("/{id}")
    SubstanceDto findById(@PathVariable("id") Long substanceId){
        return substanceFacade.findById(substanceId);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    SubstanceDto create(@Valid @RequestBody SubstanceDto substanceDto,
                                      final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return substanceFacade.create(substanceDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    SubstanceDto update(@RequestBody SubstanceDto substanceDto,
                        @PathVariable("id") Long substanceId) {
        return substanceFacade.update(substanceDto, substanceId);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long substanceId) {
        substanceFacade.deleteById(substanceId);
    }


}
