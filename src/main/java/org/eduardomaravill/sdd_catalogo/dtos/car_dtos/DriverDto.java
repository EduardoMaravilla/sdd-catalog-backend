package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private Long id;

    @Max(200)
    @Min(-200)
    @NotNull
    private Integer drive;

    @Max(10)
    @Min(-10)
    @NotNull
    private Integer direction;

    @Max(10)
    @Min(-10)
    @NotNull
    private Integer downForce;

    @NotNull
    private Boolean controlTraction;

    @NotNull
    private InitSkidDto initSkidDto;
}
