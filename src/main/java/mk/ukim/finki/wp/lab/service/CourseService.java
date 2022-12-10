package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Type;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course getCourseById(Long courseId);
    Course saveCourse(String name, String description, Long teacherId, Type type, boolean save);
    void deleteById(Long id);
    Optional<Course> getCourseByName(String name);
    List<Course> searchByCourseNameorDescription(String search);
}
