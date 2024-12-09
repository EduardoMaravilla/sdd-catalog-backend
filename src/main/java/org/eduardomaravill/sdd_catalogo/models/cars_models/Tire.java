package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tires")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tire {
    @Id
    @Column(name = "id_tires")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street_type_id", nullable = false)
    @NotNull
    private StreetType streetType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", nullable = false)
    @NotNull
    private LevelEntity levelEntity;
}
