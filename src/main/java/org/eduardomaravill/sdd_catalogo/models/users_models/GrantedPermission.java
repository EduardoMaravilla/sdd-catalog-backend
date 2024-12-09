package org.eduardomaravill.sdd_catalogo.models.users_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "granted_permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantedPermission{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_granted_permissions")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    @NotNull
    private Operation operation;

}
