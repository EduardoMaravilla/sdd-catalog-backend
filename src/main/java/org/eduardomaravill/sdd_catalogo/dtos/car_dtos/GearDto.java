package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GearDto {
    private Long id;

    @Max(10)
    @NotNull
    private Integer gearValue;

    @NotNull
    private LevelDto levelDto;
}
