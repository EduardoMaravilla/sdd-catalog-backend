package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Suspension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISuspensionRepository extends JpaRepository<Suspension,Long> {

    @Query("SELECT s FROM Suspension s WHERE s.id = :id")
    Optional<Suspension> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Suspension s " +
            "WHERE s.streetType.id = :streetTypeId AND s.levelEntity.id = :levelId")
    boolean existsSuspension(@Param("streetTypeId") Long streetTypeId, @Param("levelId") Long levelId);
}
