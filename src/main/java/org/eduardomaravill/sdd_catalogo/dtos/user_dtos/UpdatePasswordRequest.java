package org.eduardomaravill.sdd_catalogo.dtos.user_dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @Size(max = 128,min = 8)
    @NotBlank
    private String password;

    @Size(max = 128,min = 8)
    @NotBlank
    private String confirmPassword;
}
