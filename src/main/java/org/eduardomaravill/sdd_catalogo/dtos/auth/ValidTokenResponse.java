package org.eduardomaravill.sdd_catalogo.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidTokenResponse {
    private Boolean valid;
}
