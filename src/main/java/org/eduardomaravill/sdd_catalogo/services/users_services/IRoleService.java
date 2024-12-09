package org.eduardomaravill.sdd_catalogo.services.users_services;

import org.eduardomaravill.sdd_catalogo.models.users_models.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findDefaultRole();
}
