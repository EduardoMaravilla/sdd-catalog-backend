package org.eduardomaravill.sdd_catalogo.services.files;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

@Service
@Log4j2
public class FileService {

    public void writeToFile(String filePath, byte[] data) throws IOException {
        if (filePath == null || data == null) {
            throw new IllegalArgumentException("File path and data must not be null.");
        }
        Files.write(Path.of(filePath), data);
    }

    public void deleteFile(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("File path must not be null.");
        }
        Files.deleteIfExists(Path.of(filePath));
    }

    public void setPermissions(String filePath, Set<PosixFilePermission> permissions) throws IOException {
        if (filePath == null || permissions == null) {
            throw new IllegalArgumentException("File path and permissions must not be null.");
        }

        if (isWindows()) {
            log.info("Skipping POSIX permissions for file '{}' as the OS is Windows.", filePath);
            return;
        }

        try {
            Files.setPosixFilePermissions(Path.of(filePath), permissions);
            log.info("POSIX permissions set successfully for file '{}'.", filePath);
        } catch (UnsupportedOperationException e) {
            throw new IOException("Failed to set POSIX permissions for file '" + filePath + "'. Ensure the system supports POSIX file attributes.", e);
        }
    }

    private boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win");
    }
}
