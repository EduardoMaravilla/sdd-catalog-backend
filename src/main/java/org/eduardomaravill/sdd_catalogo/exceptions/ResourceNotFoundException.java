package org.eduardomaravill.sdd_catalogo.exceptions;
/**
 * Exception thrown when a resource is not found.
 */

public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param resourceName name of the class throw error
     * @param resourceId is the id of the resource not found
     */
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s with ID %d not found", resourceName, resourceId));
    }
}
