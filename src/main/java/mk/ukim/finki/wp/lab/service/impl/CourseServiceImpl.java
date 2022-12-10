package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final GradeService gradeService;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService, TeacherService teacherService, GradeService gradeService, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;

        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        return course.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = studentService.findByUsername(username);
        Course course = courseRepository.findById(courseId).get();
        List<Student> students=course.getStudents();
        if(!students.contains(student)) {
            students.add(student);
        }
        course.setStudents(students);
        Grade studentGrade = gradeRepository.getGradeByStudentAndCourse(student,course);
        if(studentGrade==null){
            gradeService.save(student,course,null,null);
        }
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> listAll(){
        return courseRepository.findAll().stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    @Transactional
    public Course saveCourse(String name, String description, Long teacherId, Type type,boolean save) {

        Teacher teacher = teacherService.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId));

        if(!save) {
            if (courseRepository.findByName(name).isPresent()) {
                throw new CourseAlreadyExistsException(name);
            }
        }
        return courseRepository.save(new Course(name, description, teacher,type));
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public List<Course> searchByCourseNameorDescription(String search) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getDescription().toLowerCase().equals(search.toLowerCase()) ||
                        c.getName().toLowerCase().equals(search.toLowerCase())).collect(Collectors.toList());
    }
}
