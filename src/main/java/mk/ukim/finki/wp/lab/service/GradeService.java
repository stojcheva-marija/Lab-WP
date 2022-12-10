package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService{
    List<Grade> getAllGradesFromCourse(Long courseId);
    void save(Student student, Course course, Character grade, LocalDateTime timestamp);
}
