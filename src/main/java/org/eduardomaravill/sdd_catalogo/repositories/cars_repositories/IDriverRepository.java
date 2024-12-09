package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT d FROM Driver d JOIN FETCH d.initSkid WHERE d.id = :id")
    Optional<Driver> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM Driver d " +
            "WHERE d.drive = :drive AND d.direction = :direction AND d.downForce = :downForce AND " +
            "d.controlTraction = :controlTraction AND d.initSkid.id = :idInitSkid")
    boolean existsDriver(@Param("drive") Integer drive,
                         @Param("direction") Integer direction,
                         @Param("downForce") Integer downForce,
                         @Param("controlTraction") Boolean controlTraction,
                         @Param("idInitSkid") Long idInitSkid);

    @Query("SELECT d FROM Driver d " +
            "WHERE d.drive = :drive AND d.direction = :direction AND d.downForce = :downForce AND " +
            "d.controlTraction = :controlTraction AND d.initSkid.id = :idInitSkid")
    Optional<Driver> driverExists(@Param("drive") Integer drive,
                                  @Param("direction") Integer direction,
                                  @Param("downForce") Integer downForce,
                                  @Param("controlTraction") Boolean controlTraction,
                                  @Param("idInitSkid") Long idInitSkid);
}
