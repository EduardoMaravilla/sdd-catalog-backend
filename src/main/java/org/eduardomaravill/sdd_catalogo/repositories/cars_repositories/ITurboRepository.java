package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Turbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITurboRepository extends JpaRepository<Turbo, Long> {

    @Query("SELECT t FROM Turbo t WHERE t.id = :id")
    Optional<Turbo> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM Turbo t " +
            "WHERE t.turboType.id = :turboTypeId AND t.levelEntity.id = :levelId")
    boolean existsTurbo(@Param("levelId") Long levelId, @Param("turboTypeId") Long turboTypeId);
}
