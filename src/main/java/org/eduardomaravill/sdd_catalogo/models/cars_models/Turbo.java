package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turbos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turbo {
    @Id
    @Column(name = "id_turbos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turbo_type_id",nullable = false)
    @NotNull
    private TurboType turboType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id",nullable = false)
    @NotNull
    private LevelEntity levelEntity;
}
