package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGearRepository extends JpaRepository<Gear, Long> {

    @Query("SELECT g FROM Gear g WHERE g.id = :id")
    Optional<Gear> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Gear g WHERE g.gearValue = :gear")
    boolean existsByGear(@Param("gear") Integer gear);
}
