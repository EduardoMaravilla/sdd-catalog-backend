package org.eduardomaravill.sdd_catalogo.services.auth.implement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.models.users_models.JwtToken;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtServiceRsaImpl implements IJwtService {

    @Value("${jwtKeys.privateKeyPath}")
    private String privateKeyResource;

    @Value("${jwtKeys.publicKeyPath}")
    private String publicKeyResource;

    @Value("${security.jwt.token.expiration-in-minutes}")
    private Long expirationInMinutes;

    @Value("${security.jwt.token.email.expiration-in-minutes}")
    private Long expirationEmailInMinutes;

    private final IJwtTokenRepository jwtTokenRepository;

    @Autowired
    public JwtServiceRsaImpl(IJwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return generateTokenWithExpiration(userDetails, extraClaims, expirationInMinutes);
    }

    @Override
    public String generateTokenEmailValidate(UserDetails userDetails, Map<String, Object> extraClaims) {
        return generateTokenWithExpiration(userDetails, extraClaims, expirationEmailInMinutes);
    }

    private String generateTokenWithExpiration(UserDetails userDetails, Map<String, Object> extraClaims, long expirationMinutes) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expiresAt = issuedAt.plusMinutes(expirationMinutes);

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(localDateTimeToDate(issuedAt))
                .expiration(localDateTimeToDate(expiresAt))
                .claims(extraClaims)
                .signWith(getPrivateKey(), Jwts.SIG.RS256)
                .compact();
    }

    @Override
    public String extractUsername(String jwtToken) {
        return extractAllClaims(jwtToken).getSubject();
    }

    @Override
    public String extractJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    @Override
    public Map<String, String> extractLocationFromRequest(HttpServletRequest request) {
        String latitude = request.getHeader("Latitude");
        String longitude = request.getHeader("Longitude");
        return Map.of("latitude", latitude != null ? latitude : "MC4w",
                "longitude", longitude != null ? longitude : "MC4w");
    }

    @Override
    public Map<String, String> extractLocationFromToken(String jwtToken) {
        Claims claims = extractAllClaims(jwtToken);
        return Map.of("latitude", claims.get("latitude", String.class), "longitude", claims.get("longitude", String.class));
    }

    @Override
    public LocalDateTime extractExpiration(String jwtToken) {
        return dateToLocalDateTime(extractAllClaims(jwtToken).getExpiration());
    }

    @Override
    @Transactional
    public Optional<JwtToken> findJwtToken(String jwtToken) {
        return jwtTokenRepository.findByToken(jwtToken);
    }

    @Override
    @Transactional
    public void jwtSaveToken(JwtToken jwtToken) {
        jwtTokenRepository.save(jwtToken);
    }

    @Override
    @Transactional
    public void invalidTokenJwt(String jwtToken) {
        Optional<JwtToken> token = findJwtToken(jwtToken);
        boolean isValid = false;
        if (token.isPresent()) {
            isValid = token.get().getIsValid();
        }
        if (isValid) {
            token.get().setIsValid(false);
            token.get().setExpiration(LocalDateTime.now());
            jwtSaveToken(token.get());
        }
    }

    private Claims extractAllClaims(String jwtToken) {
        try {
            return Jwts.parser()
                    .verifyWith(getPublicKey())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new IllegalStateException("Token has expired", e);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid token", e);
        }

    }

    private PrivateKey getPrivateKey() {

        try {
            Path privateKeyPath = Path.of(privateKeyResource);
            if (!Files.exists(privateKeyPath)) {
                throw new FileNotFoundException("File not found: " + privateKeyResource);
            }
            String privateKeyPEM = Files.readString(privateKeyPath)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IllegalStateException("Could not load private key");
        }
    }

    private PublicKey getPublicKey() {
        try {
            Path publicKeyPath = Path.of(publicKeyResource);
            if (!Files.exists(publicKeyPath)) {
                throw new FileNotFoundException("File not found: " + publicKeyResource);
            }
            String publicKeyPEM = Files.readString(publicKeyPath)
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");
            byte[] keyBytes = java.util.Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new IllegalStateException("Could not load public key");
        }
    }

    private Date localDateTimeToDate(LocalDateTime localDate) {
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    private LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


}
