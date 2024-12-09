package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "makers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maker {
    @Id
    @Column(name = "id_makers")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotBlank
    private String name;
}
