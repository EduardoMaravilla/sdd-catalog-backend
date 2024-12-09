package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auxiliaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auxiliary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auxiliaries")
    private Long id;

    @Size(max = 25)
    @NotBlank
    private String auxiliar;

    @ManyToOne
    @JoinColumn(name = "level_id",nullable = false)
    @NotNull
    private LevelEntity levelEntity;
}
