package com.example.universitymanagementsystem.controller.student;


import com.example.universitymanagementsystem.dto.student.StudentDTO;
import com.example.universitymanagementsystem.entity.Student;
import com.example.universitymanagementsystem.request.CourseRequest;
import com.example.universitymanagementsystem.service.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService service;

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        service.addStudent(student);
    }

    @PostMapping("/add/course")
    public void addCourseByCourseId(@RequestBody CourseRequest request) {
        service.addCourse(request.id(), request.courseId());
    }

    @PutMapping("/update/{id}")
    public void updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        service.updateStudentById(id, student);
    }

    @GetMapping("/get/id/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @GetMapping("/get/email/{email}")
    public StudentDTO getStudentByEmail(@PathVariable String email) {
        return service.getStudentByEmail(email);
    }

    @GetMapping("/get/phone-number/{phoneNumber}")
    public StudentDTO getStudentByPhoneNumber(@PathVariable String phoneNumber) {
        return service.getStudentByPhoneNumber(phoneNumber);
    }

    @GetMapping("/get/all")
    public List<StudentDTO> getAllStudents() {
        return service.getAllStudents();
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        service.deleteStudentById(id);
    }

    @DeleteMapping("/delete/email/{email}")
    public void deleteStudentByEmail(@PathVariable String email) {
        service.deleteStudentByEmail(email);
    }

    @DeleteMapping("/delete/phone-number/{phoneNumber}")
    public void deleteStudentByPhoneNumber(@PathVariable String phoneNumber) {
        service.deleteStudentByPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/delete/course")
    public void deleteCourseByCourseId(@RequestBody CourseRequest request) {
        service.deleteCourse(request.id(), request.courseId());
    }
}
