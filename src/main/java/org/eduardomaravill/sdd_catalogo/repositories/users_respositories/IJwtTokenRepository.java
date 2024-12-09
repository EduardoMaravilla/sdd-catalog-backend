package org.eduardomaravill.sdd_catalogo.repositories.users_respositories;

import org.eduardomaravill.sdd_catalogo.models.users_models.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJwtTokenRepository extends JpaRepository<JwtToken,Long> {
    Optional<JwtToken> findByToken(String jwtToken);
}
