package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Auxiliary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuxiliarRepository extends JpaRepository<Auxiliary, Long> {

    @Query("SELECT a FROM Auxiliary a JOIN FETCH a.levelEntity WHERE a.id = :id")
    Optional<Auxiliary> findByIdSpecific(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Auxiliary a WHERE a.auxiliar = :auxiliar")
    boolean existsByAuxiliar(@Param("auxiliar") String auxiliar);
}
