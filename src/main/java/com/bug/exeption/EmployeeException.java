package com.bug.exeption;

public class EmployeeException extends Exception {

    private String message;

    public EmployeeException(String message) {
        this.message = message;
    }

    public EmployeeException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmployeeException{" +
                "message='" + message + '\'' +
                '}';
    }
}
