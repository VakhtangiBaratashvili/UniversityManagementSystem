package com.example.universitymanagementsystem.dto.lecturer;

import com.example.universitymanagementsystem.entity.Course;
import com.example.universitymanagementsystem.enums.Gender;
import com.example.universitymanagementsystem.enums.LecturerType;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record LecturerDTO(
        Long id,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate dateOfBirth,
        Integer age,
        String email,
        String phoneNumber,
        LecturerType lecturerType,
        List<Course> courses
) {}
