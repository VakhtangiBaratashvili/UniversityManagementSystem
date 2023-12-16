package com.example.universitymanagementsystem.exception.handler;


import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.exception.course.CourseAlreadyExistsException;
import com.example.universitymanagementsystem.exception.course.CourseNotFoundException;
import com.example.universitymanagementsystem.exception.lecturer.LecturerLimitReachedException;
import com.example.universitymanagementsystem.exception.lecturer.LecturerNotFoundException;
import com.example.universitymanagementsystem.exception.student.StudentLimitReachedException;
import com.example.universitymanagementsystem.exception.student.StudentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiErrorResponseHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> studentNotFoundExceptionResponseEntity(
            StudentNotFoundException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(StudentLimitReachedException.class)
    public ResponseEntity<ApiErrorResponse> studentLimitReachedExceptionResponseEntity(
            StudentLimitReachedException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(LecturerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> lecturerNotFoundExceptionResponseEntity(
            LecturerNotFoundException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(LecturerLimitReachedException.class)
    public ResponseEntity<ApiErrorResponse> lecturerLimitReachedExceptionResponseEntity(
            LecturerLimitReachedException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> courseNotFoundExceptionResponseEntity(
            CourseNotFoundException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> courseAlreadyExistsExceptionResponseEntity(
            CourseAlreadyExistsException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> emailAlreadyExistsExceptionResponseEntity(
            EmailAlreadyExistsException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<ApiErrorResponse> emailNotValidExceptionResponseEntity(
            EmailNotValidException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> phoneNumberlreadyExistsExceptionResponseEntity(
            PhoneNumberAlreadyExistsException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(PhoneNumberNotValidException.class)
    public ResponseEntity<ApiErrorResponse> phoneNumberNotValidExceptionResponseEntity(
            PhoneNumberNotValidException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(DateOfBirthNotUpdatableException.class)
    public ResponseEntity<ApiErrorResponse> dateOfBirthNotUpdatableExceptionResponseEntity(
            DateOfBirthNotUpdatableException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, BAD_REQUEST);
    }
}
