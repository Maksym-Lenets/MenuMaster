package com.menumaster.exception;

/**
 * This exception is thrown when a request, such as a findById request, is made,
 * but no record with the specified ID is found, resulting in a
 * {@link ItemNotFoundException}.
 */
public class ItemNotFoundException extends RuntimeException {
    /**
     * Constructor for NotFoundException.
     *
     * @param message - giving message.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
