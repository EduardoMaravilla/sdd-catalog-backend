package org.eduardomaravill.sdd_catalogo.dtos.user_dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDto {

    @Size(max = 50, min = 5)
    @NotBlank
    private String name;

    @Size(max = 50, min = 5)
    @NotBlank
    private String username;

    @Size(max = 255)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Size(max = 128,min = 8)
    @NotBlank
    private String password;

    @Size(max = 128,min = 8)
    @NotBlank
    private String repeatedPassword;

    private Double latitude;

    private Double longitude;

}
