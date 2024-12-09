package org.eduardomaravill.sdd_catalogo.configs.security.private_keys;

import org.eduardomaravill.sdd_catalogo.services.files.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Base64;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrivateKeysInitializerTest {
    @Mock
    private FileService fileService;

    private PrivateKeysInitializer keysInitializer;

    @BeforeEach
    void setUp() {
        keysInitializer = new PrivateKeysInitializer(fileService);
    }

    @Test
    void shouldInitializeKeysSuccessfully() throws IOException {
        // Mock values
        String privateKeyBase64 = Base64.getEncoder().encodeToString("privateKey".getBytes());
        String publicKeyBase64 = Base64.getEncoder().encodeToString("publicKey".getBytes());

        // Inject mock values
        keysInitializer.setPrivateKeyBase64Part1(privateKeyBase64);
        keysInitializer.setPrivateKeyBase64Part2("");
        keysInitializer.setPublicKeyBase64(publicKeyBase64);
        keysInitializer.setPrivateKeyPath("/tmp/private.key");
        keysInitializer.setPublicKeyPath("/tmp/public.key");

        // Execute
        keysInitializer.initializeKeys();

        // Verify interactions
        verify(fileService, times(1)).writeToFile(eq("/tmp/private.key"), any());
        verify(fileService, times(1)).writeToFile(eq("/tmp/public.key"), any());
    }

    @Test
    void shouldCleanupKeysOnShutdown() throws IOException {
        keysInitializer.setPrivateKeyPath("/tmp/private.key");
        keysInitializer.setPublicKeyPath("/tmp/public.key");

        keysInitializer.cleanupKeys();

        verify(fileService, times(1)).deleteFile("/tmp/private.key");
        verify(fileService, times(1)).deleteFile("/tmp/public.key");
    }

}