package com.lyspaul.studentApp.exception;

public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String message) {
        super(message);
    }
}
