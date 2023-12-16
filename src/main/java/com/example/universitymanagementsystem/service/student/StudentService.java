package com.example.universitymanagementsystem.service.student;

import com.example.universitymanagementsystem.dto.student.StudentDTO;
import com.example.universitymanagementsystem.entity.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);

    void updateStudentById(Long id, Student student);

    StudentDTO getStudentById(Long id);

    StudentDTO getStudentByEmail(String email);

    StudentDTO getStudentByPhoneNumber(String phoneNumber);

    List<StudentDTO> getAllStudents();

    void deleteStudentById(Long id);

    void deleteStudentByEmail(String email);

    void deleteStudentByPhoneNumber(String phoneNumber);

    void addCourse(Long studentId, Long courseId);

    void deleteCourse(Long studentId, Long courseId);
}
