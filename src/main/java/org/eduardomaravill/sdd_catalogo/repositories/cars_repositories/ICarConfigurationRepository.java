package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.CarConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICarConfigurationRepository extends JpaRepository<CarConfiguration,Long>, JpaSpecificationExecutor<CarConfiguration> {

    @Query("SELECT c FROM CarConfiguration c " +
            "JOIN FETCH c.driver " +
            "WHERE c.id = :id")
    Optional<CarConfiguration> findByIdSpecific(@Param("id") Long id);

}
