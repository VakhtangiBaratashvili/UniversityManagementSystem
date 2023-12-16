package com.example.universitymanagementsystem.service.student;

import com.example.universitymanagementsystem.dto.student.StudentDTO;
import com.example.universitymanagementsystem.entity.Course;
import com.example.universitymanagementsystem.entity.Student;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.exception.course.CourseNotFoundException;
import com.example.universitymanagementsystem.exception.student.StudentNotFoundException;
import com.example.universitymanagementsystem.repository.course.CourseRepository;
import com.example.universitymanagementsystem.repository.lecturer.LecturerRepository;
import com.example.universitymanagementsystem.repository.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.universitymanagementsystem.regex.Validation.*;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;

    @Override
    public void addStudent(Student student) {
        if (!isEmailValid(student.getEmail())) {
            throw new EmailNotValidException(
                    "Email " + student.getEmail() + " is not valid"
            );
        }
        if (!isPhoneNumberValid(student.getPhoneNumber())) {
            throw new PhoneNumberNotValidException(
                    "Phone number " + student.getPhoneNumber() + " is not valid, " +
                            "note that number doesn't start with +995"
            );
        }
        if (studentRepository.existsByEmail(student.getEmail()) ||
                lecturerRepository.existsByEmail(student.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email " + student.getEmail() + " already exists"
            );
        }
        if (studentRepository.existsByPhoneNumber(student.getPhoneNumber()) ||
                lecturerRepository.existsByPhoneNumber(student.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(
                    "Phone Number " + student.getPhoneNumber() + " already exists"
            );
        }
        studentRepository.save(student);
    }

    @Override
    public void updateStudentById(Long id, Student student) {
        if (student != null) {
            if (!isEmailValid(student.getEmail())) {
                throw new PhoneNumberNotValidException(
                        "Phone number " + student.getPhoneNumber() + " is not valid"
                );
            }
            if (!isPhoneNumberValid(student.getPhoneNumber())) {
                throw new PhoneNumberNotValidException(
                        "Phone number " + student.getPhoneNumber() + " is not valid, " +
                                "note that number doesn't start with +995"
                );
            }
            Student getStudent = studentRepository.findById(id).orElseThrow(() ->
                    new StudentNotFoundException("Student with id " + id + " not found")
            );
            if (!student.getEmail().equals(getStudent.getEmail())) {
                if (studentRepository.existsByEmail(student.getEmail()) ||
                        lecturerRepository.existsByEmail(student.getEmail())) {
                    throw new EmailAlreadyExistsException(
                            "Email " + student.getEmail() + " already exists"
                    );
                }
            }
            if (!student.getPhoneNumber().equals(getStudent.getPhoneNumber())) {
                if (studentRepository.existsByPhoneNumber(student.getPhoneNumber()) ||
                        lecturerRepository.existsByPhoneNumber(student.getPhoneNumber())) {
                    throw new PhoneNumberAlreadyExistsException(
                            "Phone Number " + student.getPhoneNumber() + " already exists"
                    );
                }
            }
            if (!student.getDateOfBirth().equals(getStudent.getDateOfBirth())) {
                throw new DateOfBirthNotUpdatableException(
                        "Date of birth can not be changed"
                );
            }
            if (student.getFirstName() != null && !student.getFirstName().isBlank()) {
                getStudent.setFirstName(student.getFirstName());
            }
            if (student.getLastName() != null && !student.getLastName().isBlank()) {
                getStudent.setLastName(student.getLastName());
            }
            if (student.getGender() != null) {
                getStudent.setGender(student.getGender());
            }
            if (student.getEmail() != null && !student.getEmail().isBlank()) {
                getStudent.setEmail(student.getEmail());
            }
            if (student.getPhoneNumber() != null && !student.getPhoneNumber().isBlank()) {
                getStudent.setPhoneNumber(student.getPhoneNumber());
            }
            studentRepository.save(getStudent);
        }
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student with id " + id + " not found"
            );
        }
        Student student = studentRepository.getStudentById(id);
        return dtoMapper(student);
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        if (!studentRepository.existsByEmail(email)) {
            throw new StudentNotFoundException(
                    "Student with email " + email + " not found"
            );
        }
        Student student = studentRepository.getStudentByEmail(email);
        return dtoMapper(student);
    }

    @Override
    public StudentDTO getStudentByPhoneNumber(String phoneNumber) {
        if (!studentRepository.existsByPhoneNumber(phoneNumber)) {
            throw new StudentNotFoundException(
                    "Student with phone number " + phoneNumber + " not found"
            );
        }
        Student student = studentRepository.getStudentByPhoneNumber(phoneNumber);
        return dtoMapper(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student ->
          StudentDTO.
                  builder().
                  id(student.getId()).
                  firstName(student.getFirstName()).
                  lastName(student.getLastName()).
                  gender(student.getGender()).
                  dateOfBirth(student.getDateOfBirth()).
                  age(student.getAge()).
                  email(student.getEmail()).
                  phoneNumber(student.getPhoneNumber()).
                  courses(student.getCourses()).build()
        ).toList();
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student with id " + id + " not found"
            );
        }
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteStudentByEmail(String email) {
        if (!studentRepository.existsByEmail(email)) {
            throw new StudentNotFoundException(
                    "Student with email " + email + " not found"
            );
        }
        studentRepository.deleteByEmail(email);
    }

    @Override
    public void deleteStudentByPhoneNumber(String phoneNumber) {
        if (!studentRepository.existsByPhoneNumber(phoneNumber)) {
            throw new StudentNotFoundException(
                    "Student with phone number " + phoneNumber + " not found"
            );
        }
        studentRepository.deleteByPhoneNumber(phoneNumber);
    }


    @Override
    public void addCourse(Long studentId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException("Course with id " + courseId + " not found")
        );

        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student with id " + studentId + " not found")
        );
        student.addCourse(course);

        studentRepository.save(student);
    }

    @Override
    public void deleteCourse(Long studentId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException("Course with id " + courseId + " not found")
        );

        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student with id " + studentId + " not found")
        );
        student.deleteCourse(course);

        studentRepository.save(student);
    }

    private StudentDTO dtoMapper(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getDateOfBirth(),
                student.getAge(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getCourses()
        );
    }
}
