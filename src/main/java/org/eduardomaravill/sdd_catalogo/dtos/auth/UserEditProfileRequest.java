package org.eduardomaravill.sdd_catalogo.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditProfileRequest {

    @NotBlank
    @Size(max = 7)
    private String colorEdit;

    @Size(max = 50, min = 5)
    @NotBlank
    private String nameEdit;

    @Size(max = 50, min = 5)
    @NotBlank
    private String usernameEdit;

    @Size(max = 255)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String emailEdit;

    @NotBlank
    @Size(min = 4, max = 10)
    private String roleEdit;

    @Size(max = 128,min = 8)
    @NotBlank
    private String passwordEdit;
}
