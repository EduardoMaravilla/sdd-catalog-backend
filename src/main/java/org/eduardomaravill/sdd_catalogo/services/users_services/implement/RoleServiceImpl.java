package org.eduardomaravill.sdd_catalogo.services.users_services.implement;

import org.eduardomaravill.sdd_catalogo.models.users_models.Role;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IRoleRepository;
import org.eduardomaravill.sdd_catalogo.services.users_services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {


    private final String defaultRole;

    private final IRoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(IRoleRepository roleRepository, @Value("${security.default.role}") String defaultRole) {
        this.roleRepository = roleRepository;
        this.defaultRole = defaultRole;
    }

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }
}
