package com.example.universitymanagementsystem.service.lecturer;

import com.example.universitymanagementsystem.dto.lecturer.LecturerDTO;
import com.example.universitymanagementsystem.entity.Lecturer;

import java.util.List;

public interface LecturerService {

    void addLecturer(Lecturer lecturer);

    void updateLecturerById(Long id, Lecturer lecturer);

    LecturerDTO getLecturerById(Long id);

    LecturerDTO getLecturerByEmail(String email);

    LecturerDTO getLecturerByPhoneNumber(String phoneNumber);

    List<LecturerDTO> getAllLecturers();

    void deleteLecturerById(Long id);

    void deleteLecturerByEmail(String email);

    void deleteLecturerByPhoneNumber(String phoneNumber);

    void addCourse(Long lecturerId, Long courseId);

    void deleteCourse(Long lecturerId, Long courseId);
}
