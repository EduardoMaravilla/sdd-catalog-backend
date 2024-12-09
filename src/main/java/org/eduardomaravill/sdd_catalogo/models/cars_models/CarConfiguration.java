package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars_configurations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cars_configurations")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id",nullable = false)
    @NotNull
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "induction_level",nullable = false)
    @NotNull
    private LevelEntity inductionLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ecu_level",nullable = false)
    @NotNull
    private LevelEntity ecuLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "injection_level",nullable = false)
    @NotNull
    private LevelEntity injectionLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "escape_level",nullable = false)
    @NotNull
    private LevelEntity escapeLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turbo_id",nullable = false)
    @NotNull
    private Turbo turbo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nitro_level")
    @NotNull
    private LevelEntity nitroLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suspension_id",nullable = false)
    @NotNull
    private Suspension suspension;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brake_level",nullable = false)
    @NotNull
    private LevelEntity brakeLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tire_id",nullable = false)
    @NotNull
    private Tire tire;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "embrague_level",nullable = false)
    @NotNull
    private LevelEntity embragueLevelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gear_id",nullable = false)
    @NotNull
    private Gear gear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "differential_level")
    @NotNull
    private LevelEntity differentialLevelEntity;

    @Max(500)
    @NotNull
    @Column(name = "top_speed")
    private Integer topSpeed;

    @Max(100)
    @NotNull
    @Column(name = "one_hundred")
    private Double oneHundred;

    @Max(5000)
    @NotNull
    private Integer power;

    @Max(5000)
    @NotNull
    private Integer par;

    @Max(100)
    @NotNull
    @Column(name = "four_hundred")
    private Double fourHundred;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id",nullable = false)
    @NotNull
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auxiliar_one",nullable = false)
    @NotNull
    private Auxiliary auxiliaryOne;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auxiliar_two",nullable = false)
    @NotNull
    private Auxiliary auxiliaryTwo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classes_id")
    @NotNull
    private Classes classes;
}
