package com.bug.exeption;

public class BugException extends Exception {
    String message;

    public BugException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BugException{" +
                "message='" + message + '\'' +
                '}';
    }
}
