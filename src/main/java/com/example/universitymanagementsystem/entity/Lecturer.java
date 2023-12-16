package com.example.universitymanagementsystem.entity;

import com.example.universitymanagementsystem.enums.Gender;
import com.example.universitymanagementsystem.enums.LecturerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "LECTURER")
public class Lecturer {

    @Id
    @SequenceGenerator(
            name = "LECTURER_ID_SEQUENCE",
            sequenceName = "LECTURER_ID_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LECTURER_ID_SEQUENCE"
    )
    @Column(
            name = "ID",
            unique = true,
            updatable = false,
            nullable = false
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

    @Column(name = "LECTURER_TYPE")
    @Enumerated(EnumType.STRING)
    private LecturerType lecturerType;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_lecturers",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Lecturer(String firstName,
                    String lastName,
                    Gender gender,
                    LocalDate dateOfBirth,
                    String email,
                    String phoneNumber,
                    LecturerType lecturerType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        setDateOfBirth(dateOfBirth);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lecturerType = lecturerType;
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
            course.addLecturer();
        }
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
        course.deleteStudent();
    }
}
