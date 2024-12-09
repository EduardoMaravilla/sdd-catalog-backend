package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassesRepository extends JpaRepository<Classes,Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Classes c WHERE LOWER(c.name) = LOWER(:name)")
    boolean existsByName(@Param("name") String name);
}
