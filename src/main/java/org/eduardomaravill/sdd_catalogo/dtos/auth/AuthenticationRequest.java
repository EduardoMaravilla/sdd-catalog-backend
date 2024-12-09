package org.eduardomaravill.sdd_catalogo.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Size(max = 255, min = 5)
    @NotBlank
    private String usernameOrEmail;

    @Size(max = 128,min = 8)
    @NotBlank
    private String password;


    private Double latitude;


    private Double longitude;

}
