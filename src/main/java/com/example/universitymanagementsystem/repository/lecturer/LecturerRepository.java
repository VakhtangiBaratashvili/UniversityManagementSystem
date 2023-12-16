package com.example.universitymanagementsystem.repository.lecturer;

import com.example.universitymanagementsystem.entity.Lecturer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Lecturer getLecturerById(Long lecturerId);
    Lecturer getLecturerByEmail(String email);
    Lecturer getLecturerByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    void deleteByPhoneNumber(String phoneNumber);
}
