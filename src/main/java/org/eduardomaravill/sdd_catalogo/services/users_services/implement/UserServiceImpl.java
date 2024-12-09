package org.eduardomaravill.sdd_catalogo.services.users_services.implement;

import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UserSaveDto;
import org.eduardomaravill.sdd_catalogo.exceptions.InvalidPasswordException;
import org.eduardomaravill.sdd_catalogo.exceptions.ObjectNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.UsernameOrEmailDuplicateException;
import org.eduardomaravill.sdd_catalogo.models.users_models.Role;
import org.eduardomaravill.sdd_catalogo.models.users_models.User;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IUserRepository;
import org.eduardomaravill.sdd_catalogo.services.users_services.IRoleService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IRoleService roleService;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, IRoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User registerOneRacer(UserSaveDto newUser) {
        if (findOneByUsername(newUser.getUsername()).isPresent()) {
            throw new UsernameOrEmailDuplicateException("User already exists");
        }
        if (findOneByEmail(newUser.getEmail()).isPresent()) {
            throw new UsernameOrEmailDuplicateException("Email already exists");
        }
        validatePassword(newUser.getPassword(), newUser.getRepeatedPassword());
        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        Role defaultRole = roleService.findDefaultRole()
                .orElseThrow(()-> new ObjectNotFoundException("Default role not found"));
        user.setRole(defaultRole);
        user.setEmailValid(false);
        user.setColorProfile("#ff0000");
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> updatePasswordUser(User user, UpdatePasswordRequest updatePasswordRequest) {
        validatePassword(updatePasswordRequest.getPassword(), updatePasswordRequest.getConfirmPassword());
        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    private void validatePassword(String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword)){
            throw new InvalidPasswordException("Passwords do not match");
        }
        if (!StringUtils.hasText(password) || !StringUtils.hasText(repeatedPassword)) {
            throw new InvalidPasswordException("Passwords do not match");
        }
    }
}
