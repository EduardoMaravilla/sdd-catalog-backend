package org.eduardomaravill.sdd_catalogo.models.users_models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jwt_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jwt_tokens")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Size(max = 2048)
    @NotBlank
    private String token;

    @NotNull
    private LocalDateTime expiration;

    @NotNull
    @Column(name = "is_valid")
    private Boolean isValid;
}
