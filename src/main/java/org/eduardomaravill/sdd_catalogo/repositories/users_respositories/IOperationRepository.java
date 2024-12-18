package org.eduardomaravill.sdd_catalogo.repositories.users_respositories;

import org.eduardomaravill.sdd_catalogo.models.users_models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOperationRepository extends JpaRepository<Operation,Long> {
    @Query("SELECT o FROM Operation o WHERE o.permitAll = true")
    List<Operation> findByPublicAccess();

    Optional<Operation> findByName(String operation);
}
