package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turbo_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurboType {
    @Id
    @Column(name = "id_turbo_types")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @NotBlank
    private String type;
}
