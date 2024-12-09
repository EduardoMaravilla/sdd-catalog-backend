package org.eduardomaravill.sdd_catalogo.repositories.users_cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.usercarconfig_models.UserCarConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCarConfigurationRepository extends JpaRepository<UserCarConfiguration, Long> {

}
