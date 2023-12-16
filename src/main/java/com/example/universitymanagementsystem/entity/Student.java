package com.example.universitymanagementsystem.entity;

import com.example.universitymanagementsystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @SequenceGenerator(
            name = "STUDENT_ID_SEQUENCE",
            sequenceName = "STUDENT_ID_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDENT_ID_SEQUENCE"
    )
    @Column(
            name = "ID"
    )
    private Long id;

    @Column(
            name = "FIRST_NAME",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "LAST_NAME",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "GENDER",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(
            name = "DATE_OF_BIRTH",
            nullable = false,
            updatable = false
    )
    private LocalDate dateOfBirth;

    @Column(
            name = "AGE",
            nullable = false
    )
    private Integer age;

    @Column(
            name = "EMAIL",
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "PHONE_NUMBER",
            unique = true,
            nullable = false
    )
    private String phoneNumber;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName,
                   String lastName,
                   Gender gender,
                   LocalDate dateOfBirth,
                   String email,
                   String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        setDateOfBirth(dateOfBirth);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge();
    }

    private Integer calculateAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent();
        }
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
        course.deleteStudent();
    }
}
