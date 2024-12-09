package org.eduardomaravill.sdd_catalogo.configs.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Valid Data exceptions
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseError> handleValidationExceptions(HttpServletRequest request, ConstraintViolationException ex) {

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Validation exception");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseError);
    }

    private static ApiResponseError getApiResponseError(HttpServletRequest request, Exception ex, String message) {
        ApiResponseError apiResponseError= new ApiResponseError();
        apiResponseError.setBackendMessage(ex.getLocalizedMessage());
        apiResponseError.setUrl(request.getRequestURL().toString());
        apiResponseError.setMethod(request.getMethod());
        apiResponseError.setTimesTamp(LocalDateTime.now());
        apiResponseError.setMessage(message);
        return apiResponseError;
    }

    //Global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleGlobalException(HttpServletRequest request, Exception ex) {
        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Internal Server Error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseError);
    }

    //Specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleResourceNotFoundException(HttpServletRequest request,ResourceNotFoundException ex) {

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseError);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleDataNotFoundException(HttpServletRequest request,DataNotFoundException ex) {

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Data no found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseError);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<ApiResponseError> handleResourceNotFoundException(HttpServletRequest request,ResourceDuplicateException ex) {

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Duplicate resource");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseError);
    }

    //Exceptions Authorization

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponseError> handleAuthenticationException(HttpServletRequest request, AuthenticationException ex){

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"you don't have credentials for this");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponseError> handlerAccessDeniedException(HttpServletRequest request,
                                                          AccessDeniedException ex){

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Access Denied");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponseError);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponseError> handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException ex){

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Path didn't exist");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiResponseError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleObjectNotFoundException(HttpServletRequest request, ObjectNotFoundException ex){

        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Object not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiResponseError);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponseError> handleInvalidPasswordException(HttpServletRequest request, InvalidPasswordException ex){
        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Invalid password");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(apiResponseError);
    }

    @ExceptionHandler(UsernameOrEmailDuplicateException.class)
    public ResponseEntity<ApiResponseError> handleUsernameOrEmailDuplicateException(HttpServletRequest request, UsernameOrEmailDuplicateException ex){
        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Username or email already exists");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(apiResponseError);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiResponseError> handleInvalidEmailException(HttpServletRequest request, InvalidEmailException ex){
        ApiResponseError apiResponseError = getApiResponseError(request, ex,"Invalid email");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(apiResponseError);
    }
}
