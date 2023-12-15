package com.iba.fertilizer_service.dto;


import com.iba.fertilizersmanager.dto.SubstanceCompact;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import lombok.*;

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

    private String image;

    private Boolean isCommon;

    private Set<SubstanceCompact> substanceSet;

}
