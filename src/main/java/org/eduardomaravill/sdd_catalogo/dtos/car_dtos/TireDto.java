package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TireDto {
    private Long id;

    @NotNull
    private StreetTypeDto streetTypeDto;

    @NotNull
    private LevelDto levelDto;
}
