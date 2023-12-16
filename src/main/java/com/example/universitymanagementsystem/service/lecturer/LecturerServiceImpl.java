package com.example.universitymanagementsystem.service.lecturer;

import com.example.universitymanagementsystem.dto.lecturer.LecturerDTO;
import com.example.universitymanagementsystem.entity.Course;
import com.example.universitymanagementsystem.entity.Lecturer;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.exception.course.CourseNotFoundException;
import com.example.universitymanagementsystem.exception.lecturer.LecturerNotFoundException;
import com.example.universitymanagementsystem.exception.student.StudentNotFoundException;
import com.example.universitymanagementsystem.repository.course.CourseRepository;
import com.example.universitymanagementsystem.repository.lecturer.LecturerRepository;
import com.example.universitymanagementsystem.repository.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.universitymanagementsystem.regex.Validation.isEmailValid;
import static com.example.universitymanagementsystem.regex.Validation.isPhoneNumberValid;

@Service
@AllArgsConstructor
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public void addLecturer(Lecturer lecturer) {
        if (!isEmailValid(lecturer.getEmail())) {
            throw new EmailNotValidException(
                    "Email " + lecturer.getEmail() + " is not valid"
            );
        }
        if (!isPhoneNumberValid(lecturer.getPhoneNumber())) {
            throw new PhoneNumberNotValidException(
                    "Phone number " + lecturer.getPhoneNumber() + " is not valid, " +
                            "note that number doesn't start with +995"
            );
        }
        if (lecturerRepository.existsByEmail(lecturer.getEmail()) ||
                studentRepository.existsByEmail(lecturer.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email " + lecturer.getEmail() + " already exists"
            );
        }
        if (lecturerRepository.existsByPhoneNumber(lecturer.getPhoneNumber()) ||
                studentRepository.existsByPhoneNumber(lecturer.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(
                    "Phone number " + lecturer.getPhoneNumber() + " already exists"
            );
        }
        lecturerRepository.save(lecturer);
    }

    @Override
    public void updateLecturerById(Long id, Lecturer lecturer) {
        if (lecturer != null) {
            if (!isEmailValid(lecturer.getEmail())) {
                throw new PhoneNumberNotValidException(
                        "Phone number " + lecturer.getPhoneNumber() + " is not valid"
                );
            }
            if (!isPhoneNumberValid(lecturer.getPhoneNumber())) {
                throw new PhoneNumberNotValidException(
                        "Phone number " + lecturer.getPhoneNumber() + " is not valid, " +
                                "note that number doesn't start with +995"
                );
            }
            Lecturer getLecturer = lecturerRepository.findById(id).orElseThrow(() ->
                    new StudentNotFoundException("Lecturer with id " + id + " not found")
            );
            if (!lecturer.getEmail().equals(getLecturer.getEmail())) {
                if (studentRepository.existsByEmail(lecturer.getEmail()) ||
                        lecturerRepository.existsByEmail(lecturer.getEmail())) {
                    throw new EmailAlreadyExistsException(
                            "Email " + lecturer.getEmail() + " already exists"
                    );
                }
            }
            if (!lecturer.getPhoneNumber().equals(getLecturer.getPhoneNumber())) {
                if (studentRepository.existsByPhoneNumber(lecturer.getPhoneNumber()) ||
                        lecturerRepository.existsByPhoneNumber(lecturer.getPhoneNumber())) {
                    throw new PhoneNumberAlreadyExistsException(
                            "Phone Number " + lecturer.getPhoneNumber() + " already exists"
                    );
                }
            }
            if (!lecturer.getDateOfBirth().equals(getLecturer.getDateOfBirth())) {
                throw new DateOfBirthNotUpdatableException(
                        "Date of birth can not be changed"
                );
            }
            if (lecturer.getFirstName() != null && !lecturer.getFirstName().isBlank()) {
                getLecturer.setFirstName(lecturer.getFirstName());
            }
            if (lecturer.getLastName() != null && !lecturer.getLastName().isBlank()) {
                getLecturer.setLastName(lecturer.getLastName());
            }
            if (lecturer.getGender() != null) {
                getLecturer.setGender(lecturer.getGender());
            }
            if (lecturer.getEmail() != null && !lecturer.getEmail().isBlank()) {
                getLecturer.setEmail(lecturer.getEmail());
            }
            if (lecturer.getPhoneNumber() != null && !lecturer.getPhoneNumber().isBlank()) {
                getLecturer.setPhoneNumber(lecturer.getPhoneNumber());
            }
            if (lecturer.getLecturerType() != null) {
                getLecturer.setLecturerType(lecturer.getLecturerType());
            }
            lecturerRepository.save(getLecturer);
        }
    }

    @Override
    public LecturerDTO getLecturerById(Long id) {
        if (!lecturerRepository.existsById(id)) {
            throw new LecturerNotFoundException(
                    "Lecturer with id " + id + " not found"
            );
        }
        Lecturer lecturer = lecturerRepository.getLecturerById(id);
        return dtoMapper(lecturer);
    }

    @Override
    public LecturerDTO getLecturerByEmail(String email) {
        if (!lecturerRepository.existsByEmail(email)) {
            throw new LecturerNotFoundException(
                    "Lecturer with email " + email + " not found"
            );
        }
        Lecturer lecturer = lecturerRepository.getLecturerByEmail(email);
        return dtoMapper(lecturer);
    }

    @Override
    public LecturerDTO getLecturerByPhoneNumber(String phoneNumber) {
        if (!lecturerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new LecturerNotFoundException(
                    "Lecturer with phone number " + phoneNumber + " not found"
            );
        }
        Lecturer lecturer = lecturerRepository.getLecturerByPhoneNumber(phoneNumber);
        return dtoMapper(lecturer);
    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        return lecturers.stream().map(lecturer ->
                LecturerDTO.
                        builder().
                        id(lecturer.getId()).
                        firstName(lecturer.getFirstName()).
                        lastName(lecturer.getLastName()).
                        gender(lecturer.getGender()).
                        dateOfBirth(lecturer.getDateOfBirth()).
                        age(lecturer.getAge()).
                        email(lecturer.getEmail()).
                        phoneNumber(lecturer.getPhoneNumber()).
                        lecturerType(lecturer.getLecturerType()).
                        courses(lecturer.getCourses()).build()
        ).toList();
    }

    @Override
    public void deleteLecturerById(Long id) {
        if (!lecturerRepository.existsById(id)) {
            throw new LecturerNotFoundException(
                    "Lecturer with id " + id + " not found"
            );
        }
        lecturerRepository.deleteById(id);
    }

    @Override
    public void deleteLecturerByEmail(String email) {
        if (!lecturerRepository.existsByEmail(email)) {
            throw new LecturerNotFoundException(
                    "Lecturer with email " + email + " not found"
            );
        }
        lecturerRepository.deleteByEmail(email);
    }

    @Override
    public void deleteLecturerByPhoneNumber(String phoneNumber) {
        if (!lecturerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new LecturerNotFoundException(
                    "Lecturer with phone-number " + phoneNumber + " not found"
            );
        }
        lecturerRepository.deleteByPhoneNumber(phoneNumber);
    }

    @Override
    public void addCourse(Long lecturerId, Long courseId) {
        if (!lecturerRepository.existsById(lecturerId)) {
            throw new LecturerNotFoundException(
                    "Lecturer with id " + lecturerId + " not found"
            );
        }

        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException(
                    "Course with id " + courseId + " not found"
            );
        }

        Course course = courseRepository.getCourseById(courseId);
        Lecturer lecturer = lecturerRepository.getLecturerById(lecturerId);
        lecturer.addCourse(course);

        lecturerRepository.save(lecturer);
    }

    @Override
    public void deleteCourse(Long lecturerId, Long courseId) {
        if (!lecturerRepository.existsById(lecturerId)) {
            throw new LecturerNotFoundException(
                    "Lecturer with id " + lecturerId + " not found"
            );
        }

        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException(
                    "Course with id " + courseId + " not found"
            );
        }

        Course course = courseRepository.getCourseById(courseId);
        Lecturer lecturer = lecturerRepository.getLecturerById(lecturerId);
        lecturer.deleteCourse(course);

        lecturerRepository.save(lecturer);
    }

    private LecturerDTO dtoMapper(Lecturer lecturer) {
        return new LecturerDTO(
                lecturer.getId(),
                lecturer.getFirstName(),
                lecturer.getLastName(),
                lecturer.getGender(),
                lecturer.getDateOfBirth(),
                lecturer.getAge(),
                lecturer.getEmail(),
                lecturer.getPhoneNumber(),
                lecturer.getLecturerType(),
                lecturer.getCourses()
        );
    }
}
