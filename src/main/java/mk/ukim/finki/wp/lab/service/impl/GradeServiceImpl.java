package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<Grade> getAllGradesFromCourse(Long courseId) {
        return gradeRepository.getGradesByCourseCourseId(courseId);
    }

    @Override
    public void save(Student student, Course course, Character grade, LocalDateTime timestamp) {
        List<Student> students = studentRepository.findAll();
        if(gradeRepository.getGradeByStudentAndCourse(student,course)!=null){
            gradeRepository.delete(gradeRepository.getGradeByStudentAndCourse(student,course));
        }
        gradeRepository.save(new Grade(grade,student,course,timestamp));
    }
//    @Override
//    public Grade getGradeByStudentAndCourse(Student student, Course course) {
//        System.out.println(student);
//        System.out.println(course);
//        return getGradeByStudentAndCourse(student,course);
//    }
}
