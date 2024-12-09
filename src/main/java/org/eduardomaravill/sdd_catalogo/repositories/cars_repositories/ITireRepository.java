package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Tire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITireRepository extends JpaRepository<Tire,Long> {

    @Query("SELECT t FROM Tire t WHERE t.id = :id")
    Optional<Tire> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM Tire t " +
            "WHERE t.streetType.id = :streetTypeId AND t.levelEntity.id = :levelId")
    boolean existsTire(@Param("streetTypeId") Long streetTypeId, @Param("levelId") Long levelId);
}
