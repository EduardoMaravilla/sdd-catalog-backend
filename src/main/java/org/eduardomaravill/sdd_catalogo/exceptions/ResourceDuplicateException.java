package org.eduardomaravill.sdd_catalogo.exceptions;
/**
 * Exception thrown when a resource can be duplicate.
 */

public class ResourceDuplicateException extends RuntimeException {
    /**
     * Constructs a new ResourceDuplicateException with the specified detail message.
     *
     * @param resourceName name of the class throw error
     * @param nameDuplicate is the name that exist int the db
     */
    public ResourceDuplicateException(String resourceName, String nameDuplicate) {
        super(String.format("The %s with name: %s already exists.", resourceName, nameDuplicate));
    }
}
