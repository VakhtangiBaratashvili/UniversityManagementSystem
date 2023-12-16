package com.example.universitymanagementsystem.controller.lecturer;


import com.example.universitymanagementsystem.dto.lecturer.LecturerDTO;
import com.example.universitymanagementsystem.entity.Lecturer;
import com.example.universitymanagementsystem.request.CourseRequest;
import com.example.universitymanagementsystem.service.lecturer.LecturerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v2/lecturers")
public class LecturerController {

    private final LecturerService service;

    @PostMapping("/add")
    public void addStudent(@RequestBody Lecturer lecturer) {
        service.addLecturer(lecturer);
    }

    @PostMapping("/add/course")
    public void addCourseByCourseId(@RequestBody CourseRequest request) {
        service.addCourse(request.id(), request.courseId());
    }

    @PutMapping("/update/{id}")
    public void updateStudentById(@PathVariable Long id, @RequestBody Lecturer lecturer) {
        service.updateLecturerById(id, lecturer);
    }

    @GetMapping("/get/id/{id}")
    public LecturerDTO getLecturerById(@PathVariable Long id) {
        return service.getLecturerById(id);
    }

    @GetMapping("/get/email/{email}")
    public LecturerDTO getLecturerByEmail(@PathVariable String email) {
        return service.getLecturerByEmail(email);
    }

    @GetMapping("/get/phone-number/{phoneNumber}")
    public LecturerDTO getLecturerByPhoneNumber(@PathVariable String phoneNumber) {
        return service.getLecturerByPhoneNumber(phoneNumber);
    }

    @GetMapping("/get/all")
    public List<LecturerDTO> getAllLecturers() {
        return service.getAllLecturers();
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteLecturerById(@PathVariable Long id) {
        service.deleteLecturerById(id);
    }

    @DeleteMapping("/delete/email/{email}")
    public void deleteLecturerByEmail(@PathVariable String email) {
        service.deleteLecturerByEmail(email);
    }

    @DeleteMapping("/delete/phone-number/{phoneNumber}")
    public void deleteLecturerByPhoneNumber(@PathVariable String phoneNumber) {
        service.deleteLecturerByPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/delete/course")
    public void deleteCourseByCourseId(@RequestBody CourseRequest request) {
        service.deleteCourse(request.id(), request.courseId());
    }
}
