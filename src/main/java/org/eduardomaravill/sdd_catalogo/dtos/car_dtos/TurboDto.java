package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurboDto {
    private Long id;

    @NotNull
    private TurboTypeDto turboTypeDto;

    @NotNull
    private LevelDto levelDto;
}
