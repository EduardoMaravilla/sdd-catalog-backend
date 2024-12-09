package org.eduardomaravill.sdd_catalogo.configs.security;

import org.eduardomaravill.sdd_catalogo.exceptions.ObjectNotFoundException;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityBeansInjector {

    private final IUserRepository userRepository;

    @Autowired
    public SecurityBeansInjector(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService( userDetailsService() );
        authenticationProvider.setPasswordEncoder( passwordEncoder() );
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService() {
        return (username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with username " + username)));
    }
}

