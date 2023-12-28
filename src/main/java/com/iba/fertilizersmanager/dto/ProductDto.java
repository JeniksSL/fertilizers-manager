package com.iba.fertilizersmanager.dto;


import com.iba.fertilizersmanager.dto.SubstanceCompact;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class ProductDto extends BaseDto {

    private Long id;

    private String name;

    private String fullName;

    private String color;

    private String image;

    private Boolean isCommon;

    private BigDecimal price;

    private Set<SubstanceCompact> substanceSet;

}
