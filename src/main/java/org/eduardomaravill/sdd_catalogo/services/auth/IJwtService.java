package org.eduardomaravill.sdd_catalogo.services.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.eduardomaravill.sdd_catalogo.models.users_models.JwtToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface IJwtService {
    String generateToken(UserDetails userDetails, Map<String, Object> extraClaims);
    String generateTokenEmailValidate(UserDetails userDetails, Map<String, Object> extraClaims);
    String extractUsername(String jwtToken);
    String extractJwtFromRequest(HttpServletRequest request);
    Map<String, String> extractLocationFromRequest(HttpServletRequest request);
    Map<String,String> extractLocationFromToken(String jwtToken);
    LocalDateTime extractExpiration(String jwtToken);
    Optional<JwtToken> findJwtToken(String jwtToken);
    void jwtSaveToken(JwtToken jwtToken);
    void invalidTokenJwt(String jwtToken);
}
