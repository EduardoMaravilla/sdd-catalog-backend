package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "init_skids")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitSkid {
    @Id
    @Column(name = "id_init_skids")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotBlank
    @Column(name = "skid_type")
    private String skidType;
}
