package org.eduardomaravill.sdd_catalogo.models.users_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operations")
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String name;

    @Size(max = 50)
    private String path;

    @Size(max = 10)
    @NotBlank
    @Column(name = "http_method")
    private String httpMethod;

    @NotNull
    @Column(name = "permit_all")
    private Boolean permitAll;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @NotNull
    private Module module;

}
