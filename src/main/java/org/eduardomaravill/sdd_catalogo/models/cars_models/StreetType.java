package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "street_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreetType {
    @Id
    @Column(name = "id_street_types")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotBlank
    @Column(name = "street_type")
    private String streetTypeVal;
}
