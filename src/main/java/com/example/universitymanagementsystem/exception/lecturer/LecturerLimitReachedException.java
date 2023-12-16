package com.example.universitymanagementsystem.exception.lecturer;

public class LecturerLimitReachedException extends RuntimeException {

    public LecturerLimitReachedException(String message) {
        super(message);
    }
}
