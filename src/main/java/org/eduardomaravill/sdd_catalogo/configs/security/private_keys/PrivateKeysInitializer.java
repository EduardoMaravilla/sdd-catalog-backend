package org.eduardomaravill.sdd_catalogo.configs.security.private_keys;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.eduardomaravill.sdd_catalogo.services.files.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Set;

@Component
@Log4j2
@Setter
@Getter
public class PrivateKeysInitializer {

    private final FileService fileService;

    @Value("${jwtKeys.privateKeyBase64Part1}")
    private String privateKeyBase64Part1;

    @Value("${jwtKeys.privateKeyBase64Part2}")
    private String privateKeyBase64Part2;

    @Value("${jwtKeys.publicKeyBase64}")
    private String publicKeyBase64;

    @Value("${jwtKeys.privateKeyPath}")
    private String privateKeyPath;

    @Value("${jwtKeys.publicKeyPath}")
    private String publicKeyPath;

    public PrivateKeysInitializer(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    public void initializeKeys() {
        try {
            String privateKeyBase64 = privateKeyBase64Part1 + privateKeyBase64Part2;
            validateBase64( privateKeyBase64, "privateKeyBase64");
            validateBase64(publicKeyBase64, "publicKeyBase64");

            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);

            try {
                fileService.writeToFile(privateKeyPath, privateKeyBytes);
                fileService.writeToFile(publicKeyPath, publicKeyBytes);

                fileService.setPermissions(privateKeyPath, Set.of(PosixFilePermission.OWNER_READ));
                fileService.setPermissions(publicKeyPath, Set.of(PosixFilePermission.OWNER_READ));

                log.info("Keys generated successfully. Files located at {} and {}", privateKeyPath, publicKeyPath);
            } finally {
                // Clear sensitive data from memory
                Arrays.fill(privateKeyBytes, (byte) 0);
                Arrays.fill(publicKeyBytes, (byte) 0);
            }
        } catch (Exception e) {
            log.error("Failed to initialize keys.", e);
            throw new IllegalStateException("Could not initialize keys.", e);
        }
    }

    private void validateBase64(String key, String keyName) {
        if (Objects.isNull(key) || key.isBlank()) {
            throw new IllegalArgumentException(keyName + " is missing or empty.");
        }
        try {
            Base64.getDecoder().decode(key);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(keyName + " is not a valid Base64-encoded string.", e);
        }
    }

    @PreDestroy
    public void cleanupKeys() {
        try {
            fileService.deleteFile(privateKeyPath);
            fileService.deleteFile(publicKeyPath);
            log.info("Temporary keys deleted during shutdown.");
        } catch (IOException e) {
            log.error("Failed to delete keys during shutdown.", e);
        }
    }
}
