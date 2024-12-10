package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurboTypeDto {
    private Long id;

    @Size(max = 30)
    @NotBlank
    private String type;
}