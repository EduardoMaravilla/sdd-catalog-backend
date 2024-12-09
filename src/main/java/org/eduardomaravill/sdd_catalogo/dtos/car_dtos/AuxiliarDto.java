package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuxiliarDto {

    private Long id;

    @Size(max = 25)
    @NotBlank
    private String auxiliar;

    @NotNull
    private LevelDto levelDto;
}
