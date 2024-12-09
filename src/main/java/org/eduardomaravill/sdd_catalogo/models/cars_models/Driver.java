package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Driver {
    @Id
    @Column(name = "id_drivers")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(100)
    @Min(-100)
    @NotNull
    private Integer drive;

    @Max(5)
    @Min(-5)
    @NotNull
    private Integer direction;

    @Max(5)
    @Min(-5)
    @NotNull
    @Column(name = "down_force")
    private Integer downForce;

    @NotNull
    @Column(name = "control_traction")
    private Boolean controlTraction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skid_start_id", nullable = false)
    @NotNull
    private InitSkid initSkid;

}
