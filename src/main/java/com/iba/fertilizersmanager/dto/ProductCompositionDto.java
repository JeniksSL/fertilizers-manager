package com.iba.fertilizersmanager.dto;


import com.iba.fertilizersmanager.dto.core.CompactDto;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProductCompositionDto extends CompactDto {
    private Long id;
    private Set<SubstanceCompact> substanceSet;

    public ProductCompositionDto(Long id) {
        this.id = id;
        this.substanceSet=null;
    }
}
