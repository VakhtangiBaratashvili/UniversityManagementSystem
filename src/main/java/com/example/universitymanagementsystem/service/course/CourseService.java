package com.example.universitymanagementsystem.service.course;

import com.example.universitymanagementsystem.dto.course.CourseDTO;
import com.example.universitymanagementsystem.entity.Course;

import java.util.List;

public interface CourseService {

    void addCourse(Course course);

    void updateCourseById(Long id, Course course);

    CourseDTO getCourseById(Long id);

    CourseDTO getCourseByName(String name);

    List<CourseDTO> getAllCourses();

    void deleteCourseById(Long id);

    void deleteCourseByName(String name);
}
