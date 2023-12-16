package com.example.universitymanagementsystem.dto.course;

import com.example.universitymanagementsystem.entity.Lecturer;
import com.example.universitymanagementsystem.entity.Student;
import lombok.Builder;

import java.util.List;

@Builder
public record CourseDTO(
        Long id,
        String courseName,
        Integer maxStudents,
        Integer maxLecturers,
        List<Student> students,
        List<Lecturer> lecturers
) {}
