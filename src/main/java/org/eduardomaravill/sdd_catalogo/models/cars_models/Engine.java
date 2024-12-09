package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "engines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {
    @Id
    @Column(name = "id_engines")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(3000)
    @NotNull
    private Integer bhp;

    @Max(20)
    @NotNull
    private Double liters;

    @Size(max = 20)
    @NotBlank
    private String model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", nullable = false)
    @NotNull
    private LevelEntity levelEntity;
}
