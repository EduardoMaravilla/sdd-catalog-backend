package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineDto {
    private Long id;

    @Max(3000)
    @NotNull
    private Integer bhp;

    @Max(20)
    @NotNull
    private Double liters;

    @Size(max = 20)
    @NotBlank
    private String model;

    @NotNull
    private LevelDto levelDto;
}
