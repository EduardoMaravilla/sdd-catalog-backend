package org.eduardomaravill.sdd_catalogo.models.users_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modules")
    private Long id;

    @Size(max = 50)
    @Column(name = "module")
    @NotBlank
    private String moduleName;

    @Size(max = 50)
    @Column(name = "base_path")
    @NotBlank
    private String basePath;
}
