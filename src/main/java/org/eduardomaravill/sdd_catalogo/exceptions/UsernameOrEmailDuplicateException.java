package org.eduardomaravill.sdd_catalogo.exceptions;

public class UsernameOrEmailDuplicateException extends RuntimeException{

    public UsernameOrEmailDuplicateException() {
        super();
    }

    public UsernameOrEmailDuplicateException(String message) {
        super(message);
    }
}
