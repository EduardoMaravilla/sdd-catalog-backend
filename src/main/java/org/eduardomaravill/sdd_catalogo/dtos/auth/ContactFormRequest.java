package org.eduardomaravill.sdd_catalogo.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactFormRequest {
    @Size(max=255)
    @NotBlank
    private String nameContact;

    @Size(max = 255)
    @NotBlank
    private String emailContact;

    @Size(max = 255)
    @NotBlank
    private String subjectContact;

    @Size(max = 500)
    @NotBlank
    private String messageContact;
}
