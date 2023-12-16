package com.example.universitymanagementsystem.service.course;

import com.example.universitymanagementsystem.dto.course.CourseDTO;
import com.example.universitymanagementsystem.entity.Course;
import com.example.universitymanagementsystem.exception.course.CourseAlreadyExistsException;
import com.example.universitymanagementsystem.exception.course.CourseNotFoundException;
import com.example.universitymanagementsystem.repository.course.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        if (courseRepository.existsByCourseName(course.getCourseName())) {
            throw new CourseAlreadyExistsException(
                    "Course with name " + course.getCourseName() + " already exists"
            );
        }
        courseRepository.save(course);
    }

    @Override
    public void updateCourseById(Long id, Course course) {
        if (course != null) {
            Course getCourse = courseRepository.findById(id).orElseThrow(() ->
                    new CourseNotFoundException("Course with id " + id + " not found")
            );
            if (!course.getCourseName().equals(getCourse.getCourseName())) {
                if (courseRepository.existsByCourseName(course.getCourseName())) {
                    throw new CourseAlreadyExistsException(
                            "Course with name " + course.getCourseName() + " already exists"
                    );
                }
            }
            if (course.getCourseName() != null && !course.getCourseName().isBlank()) {
                getCourse.setCourseName(course.getCourseName());
            }
            if (course.getMaxStudents() != null && course.getMaxStudents() != 0) {
                getCourse.setMaxStudents(course.getMaxStudents());
            }
            if (course.getLecturers() != null && course.getMaxLecturers() != 0) {
                getCourse.setMaxLecturers(course.getMaxLecturers());
            }
            courseRepository.save(getCourse);
        }
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(
                    "Course with id " + id + " not found"
            );
        }

        Course course = courseRepository.getCourseById(id);
        return dtoMapper(course);
    }

    @Override
    public CourseDTO getCourseByName(String name) {
        if (!courseRepository.existsByCourseName(name)) {
            throw new CourseNotFoundException(
                    "Course with name " + name + " not found"
            );
        }

        Course course = courseRepository.getCourseByCourseName(name);
        return dtoMapper(course);
    }


    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(course ->
                CourseDTO.
                        builder().
                        id(course.getId()).
                        courseName(course.getCourseName()).
                        maxStudents(course.getMaxStudents()).
                        maxLecturers(course.getMaxLecturers()).
                        students(course.getStudents()).
                        lecturers(course.getLecturers()).
                        build()
                ).toList();
    }

    @Override
    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(
                    "Course with id " + id + " not found"
            );
        }
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteCourseByName(String name) {
        if (!courseRepository.existsByCourseName(name)) {
            throw new CourseNotFoundException(
                    "Course with name " + name + " not found"
            );
        }
        courseRepository.deleteByCourseName(name);
    }

    private CourseDTO dtoMapper(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getCourseName(),
                course.getMaxStudents(),
                course.getMaxLecturers(),
                course.getStudents(),
                course.getLecturers()
        );
    }
}
