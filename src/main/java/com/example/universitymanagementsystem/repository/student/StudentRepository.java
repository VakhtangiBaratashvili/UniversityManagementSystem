package com.example.universitymanagementsystem.repository.student;

import com.example.universitymanagementsystem.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentById(Long id);
    Student getStudentByEmail(String email);
    Student getStudentByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    void deleteByPhoneNumber(String phoneNumber);
}
