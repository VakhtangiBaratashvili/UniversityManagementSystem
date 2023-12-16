package com.example.universitymanagementsystem.exception.student;

public class StudentLimitReachedException extends RuntimeException {

    public StudentLimitReachedException(String message) {
        super(message);
    }
}
