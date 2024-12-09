package org.eduardomaravill.sdd_catalogo.configs.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eduardomaravill.sdd_catalogo.exceptions.ObjectNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.users_models.JwtToken;
import org.eduardomaravill.sdd_catalogo.models.users_models.User;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.eduardomaravill.sdd_catalogo.utils.GeoLocation.validateLocation;

@Component
public class JwtSAuthenticationFilter extends OncePerRequestFilter {


    private final IJwtService jwtService;

    private final IUserService userService;

    @Autowired
    public JwtSAuthenticationFilter(IJwtService jwtService, IUserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwt)){
            filterChain.doFilter(request, response);
            return;
        }
        Optional<JwtToken> token = jwtService.findJwtToken(jwt);
        boolean isValid = false;
        if (token.isPresent()){
            isValid = validateJwtToken(token.get()) &&
                    validateLocation(jwtService.extractLocationFromToken(jwt),
                            jwtService.extractLocationFromRequest(request));
        }
        if (!isValid && token.isPresent()){
            updateTokenStatus(token.get());
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extractUsername(jwt);
        User user = userService.findOneByUsername(username).orElseThrow(
                ()-> new ObjectNotFoundException("user not found. Username; " + username)
        );
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private boolean validateJwtToken(JwtToken token) {
        LocalDateTime now = LocalDateTime.now();
        return token.getIsValid() && token.getExpiration().isAfter(now);
    }

    private void updateTokenStatus(JwtToken token) {
        token.setIsValid(false);
        token.setExpiration(LocalDateTime.now());
        jwtService.jwtSaveToken(token);
    }
}
