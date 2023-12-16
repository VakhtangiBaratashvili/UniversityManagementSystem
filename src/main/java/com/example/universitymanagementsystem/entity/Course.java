package com.example.universitymanagementsystem.entity;

import com.example.universitymanagementsystem.exception.lecturer.LecturerLimitReachedException;
import com.example.universitymanagementsystem.exception.student.StudentLimitReachedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @SequenceGenerator(
            name = "COURSE_ID_SEQUENCE",
            sequenceName = "COURSE_ID_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COURSE_ID_SEQUENCE"
    )
    @Column(
            name = "ID",
            unique = true,
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(
            name = "COURSE_NAME",
            unique = true,
            nullable = false

    )
    private String courseName;

    @Column(name = "MAX_STUDENTS")
    private Integer maxStudents;

    @Column(name = "MAX_LECTURERS")
    private Integer maxLecturers;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_lecturers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "lecturer_id")
    )
    private List<Lecturer> lecturers = new ArrayList<>();

    public Course(String courseName,
                  Integer maxStudents,
                  Integer maxLecturers) {
        this.courseName = courseName;
        this.maxStudents = maxStudents;
        this.maxLecturers = maxLecturers;
    }

    public void addStudent() {
        if (this.maxStudents <= 0) {
            throw new StudentLimitReachedException(
                    "Student limit has reached"
            );
        }
        this.maxStudents--;
    }

    public void deleteStudent() {
        this.maxStudents++;
    }

    public void addLecturer() {
        if (this.maxLecturers <= 0) {
            throw new LecturerLimitReachedException(
                    "Lecturer limit has reached"
            );
        }
        this.maxLecturers--;
    }

    public void deleteLecturer() {
        this.maxLecturers++;
    }
}
