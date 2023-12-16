package com.example.universitymanagementsystem.exception.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String message,
        HttpStatus status,
        LocalDateTime timeStamp
) {}
