package com.example.universitymanagementsystem.repository.course;

import com.example.universitymanagementsystem.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseById(Long id);
    Course getCourseByCourseName(String courseName);
    boolean existsByCourseName(String courseName);
    @Transactional
    void deleteByCourseName(String courseName);
}
