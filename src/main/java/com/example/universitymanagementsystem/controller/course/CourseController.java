package com.example.universitymanagementsystem.controller.course;


import com.example.universitymanagementsystem.dto.course.CourseDTO;
import com.example.universitymanagementsystem.entity.Course;
import com.example.universitymanagementsystem.service.course.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v3/courses")
public class CourseController {

    private final CourseService service;

    @PostMapping("/add")
    public void addCourse(@RequestBody Course course) {
        service.addCourse(course);
    }

    @PutMapping("/update/{id}")
    public void updateCourseById(@PathVariable Long id, @RequestBody Course course) {
        service.updateCourseById(id, course);
    }

    @GetMapping("/get/id/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @GetMapping("/get/name/{courseName}")
    public CourseDTO getCourseByName(@PathVariable String courseName) {
        return service.getCourseByName(courseName);
    }

    @GetMapping("/get/all")
    public List<CourseDTO> getAllCourses() {
        return service.getAllCourses();
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        service.deleteCourseById(id);
    }

    @DeleteMapping("/delete/name/{courseName}")
    public void deleteCourseByName(@PathVariable String courseName) {
        service.deleteCourseByName(courseName);
    }
}
