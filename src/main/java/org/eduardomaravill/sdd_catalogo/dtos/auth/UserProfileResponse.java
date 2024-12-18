package org.eduardomaravill.sdd_catalogo.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {

    private String name;
    private String username;
    private String email;
    private String role;
    private String color;

}
