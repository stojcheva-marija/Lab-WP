package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentServiceImpl studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentServiceImpl studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Course course = courseRepository.findById(courseId);
        Student student = studentService.findByUsername(username);
        if (course == null || student == null){
            return null;
        }
        courseRepository.addStudentToCourse(student, course);
        return course;
    }

    @Override
    public List<Course> listAll(){

        return courseRepository.findAllCourses().stream()
                .sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course saveCourse(String name, String description, Long teacherId) {

        Teacher teacher = teacherService.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId));

        if(courseRepository.findByName(name).isPresent()) {
            throw new CourseAlreadyExistsException(name);
        }

        return courseRepository.saveOrUpdate(new Course(name, description, teacher));
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        return courseRepository.findByName(name);
    }
}
