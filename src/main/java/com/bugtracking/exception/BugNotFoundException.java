package com.bugtracking.exception;

public class BugNotFoundException extends Exception {
 
    private static final long serialVersionUID = 1L;

    // Default constructor
    public BugNotFoundException() {
        super();
    }

    // Constructor with a custom error message
    public BugNotFoundException(String message) {
        super(message);
    }

    // Constructor with a custom error message and a cause
    public BugNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public BugNotFoundException(Throwable cause) {
        super(cause);
    }

    // Constructor with a custom error message, a cause, and options for suppression and stack trace
    public BugNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
