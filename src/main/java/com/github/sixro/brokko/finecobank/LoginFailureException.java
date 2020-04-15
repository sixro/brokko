package com.github.sixro.brokko.finecobank;

/**
 * Represents a exception occured during login.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class LoginFailureException extends RuntimeException {

    /**
     * Create this exception with specified message.
     *
     * @param message a message
     */
    public LoginFailureException(String message) {
        super(message);
    }

    /**
     * Create this exception with specified message and cause.
     *
     * @param message a message
     * @param cause a cause
     */
    public LoginFailureException(String message, Throwable cause) {
        super(message, cause);
    }

}
