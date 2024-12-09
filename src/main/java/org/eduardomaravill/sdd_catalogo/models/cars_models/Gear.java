package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gears")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gear {
    @Id
    @Column(name = "id_gears")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(10)
    @NotNull
    @Column(name = "gear_value")
    private Integer gearValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", nullable = false)
    @NotNull
    private LevelEntity levelEntity;
}
