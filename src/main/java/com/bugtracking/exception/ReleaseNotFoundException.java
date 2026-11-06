package com.bugtracking.exception;

public class ReleaseNotFoundException extends Exception {
 
    private static final long serialVersionUID = 1L;

    // Default constructor
    public ReleaseNotFoundException() {
        super();
    }

    // Constructor with a custom error message
    public ReleaseNotFoundException(String message) {
        super(message);
    }

    // Constructor with a custom error message and a cause
    public ReleaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public ReleaseNotFoundException(Throwable cause) {
        super(cause);
    }

    // Constructor with a custom error message, a cause, and options for suppression and stack trace
    public ReleaseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
