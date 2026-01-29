package com.applicantz.test_project.exception;

/**
 * Author: Masilamony Chittebabu
 * Created: 29/01/2026
 */
public class CustomException extends Exception {

    /**
     * Constructor that extends the Exception class and throws a custom message
     * @param message custom message for the user
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * Constructor that displays the root class Throwable
     * @param message custom message for the user
     * @param cause the error message that displays the technical root cause of failure
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
