package com.iba.fertilizersmanager.dto;



import com.iba.fertilizersmanager.dto.core.CompactDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SubstanceCompact extends CompactDto {
    private Long id;
    private BigDecimal content;

}
