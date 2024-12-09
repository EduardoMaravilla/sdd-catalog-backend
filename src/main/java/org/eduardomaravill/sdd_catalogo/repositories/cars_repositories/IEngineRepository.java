package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEngineRepository extends JpaRepository<Engine, Long> {

    @Query("SELECT e FROM Engine e WHERE e.id = :id")
    Optional<Engine> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Engine e " +
            "WHERE LOWER(e.model) = LOWER(:model) AND e.bhp = :bhp AND e.liters = :liters AND e.levelEntity.id = :levelId")
    boolean existsEngine(@Param("bhp") Integer bhp, @Param("liters") Double liters,
                         @Param("model") String model, @Param("levelId") Long levelId);
}
