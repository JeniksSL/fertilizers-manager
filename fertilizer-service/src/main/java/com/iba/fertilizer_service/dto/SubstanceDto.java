package com.iba.fertilizer_service.dto;



import com.iba.fertilizersmanager.dto.core.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SubstanceDto extends BaseDto {


    private Long id;
    @NotBlank
    private String name;
    @NonNull
    private String color;

    private String description;

    private String image;

}
