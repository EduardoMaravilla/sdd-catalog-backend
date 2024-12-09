package org.eduardomaravill.sdd_catalogo.repositories.users_respositories;

import org.eduardomaravill.sdd_catalogo.models.users_models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
