package com.menumaster.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling custom exceptions and providing
 * consistent error responses for the application.
 *
 * <p>This class is annotated with {@code @RestControllerAdvice}, which makes it a
 * global exception handler for the entire application.
 *
 * <p>Extends {@code ResponseEntityExceptionHandler} to benefit from built-in
 * exception handling capabilities.
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handler method for handling {@link ItemNotFoundException}.
     *
     * <p>This method is responsible for handling the {@link ItemNotFoundException}
     * when it occurs. It returns a ResponseEntity with a status code of
     * {@link HttpStatus#NOT_FOUND} and the error message from the exception as the
     * response body.
     *
     * <p>@param itemNotFoundException The {@link ItemNotFoundException} to be handled.
     * 
     * @return A ResponseEntity with a status code of {@link HttpStatus#NOT_FOUND}
     *         and the error message.
     */
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleItemNotFoundException(
        ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<>(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler method to handle validation errors caused by the validation
     * of request parameters or payload. This method is responsible for handling the
     * {@link org.springframework.web.bind.MethodArgumentNotValidException} and
     * returning a response entity with detailed error messages.
     *
     * @param ex The
     *           {@link org.springframework.web.bind.MethodArgumentNotValidException}
     *           instance representing the validation error.
     * @return A {@link org.springframework.http.ResponseEntity} containing a
     *         {@link java.util.Map} of field errors and their corresponding error
     *         messages. The response status is set to
     *         {@link org.springframework.http.HttpStatus#BAD_REQUEST}.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(fe -> fe.getField() + " - " + fe.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler method for handling {@link IllegalArgumentException}.
     *
     * <p>This method is responsible for handling the {@link IllegalArgumentException}
     * when it occurs. It returns a ResponseEntity with a status code of
     * {@link HttpStatus#NOT_ACCEPTABLE} and the error message from the exception as
     * the response body.
     *
     * @param illegalArgumentException The {@link IllegalArgumentException} to be
     *                                 handled.
     * @return A ResponseEntity with a status code of
     *         {@link HttpStatus#NOT_ACCEPTABLE} and the error message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<String> handleIllegalArgumentException(
        IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
