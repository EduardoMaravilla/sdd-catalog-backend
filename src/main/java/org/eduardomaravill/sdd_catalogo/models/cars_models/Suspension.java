package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suspensions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suspension {
    @Id
    @Column(name = "id_suspensions")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street_type_id")
    @NotNull
    private StreetType streetType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id")
    @NotNull
    private LevelEntity levelEntity;
}
