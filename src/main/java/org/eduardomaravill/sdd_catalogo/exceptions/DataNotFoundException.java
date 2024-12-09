package org.eduardomaravill.sdd_catalogo.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String resourceName) {
        super(String.format("The name %s does not exist", resourceName));
    }
}
