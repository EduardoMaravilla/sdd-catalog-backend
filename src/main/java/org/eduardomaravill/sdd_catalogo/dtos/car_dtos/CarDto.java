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
public class CarDto {
    private Long id;

    @NotNull
    private MakerDto makerDto;

    @Size(max = 50)
    @NotBlank
    private String model;

    @Size(max = 4)
    @NotBlank
    private String year;
}
