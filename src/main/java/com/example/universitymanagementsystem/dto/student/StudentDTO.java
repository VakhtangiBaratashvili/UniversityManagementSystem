package com.example.universitymanagementsystem.dto.student;

import com.example.universitymanagementsystem.entity.Course;
import com.example.universitymanagementsystem.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record StudentDTO(
        Long id,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate dateOfBirth,
        Integer age,
        String email,
        String phoneNumber,
        List<Course> courses
) {}
