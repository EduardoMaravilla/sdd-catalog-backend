package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.InitSkid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IInitSkidRepository extends JpaRepository<InitSkid,Long> {
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM InitSkid i WHERE LOWER(i.skidType) = LOWER(:skidType)")
    boolean existsByName(@Param("skidType") String skidType);
}
