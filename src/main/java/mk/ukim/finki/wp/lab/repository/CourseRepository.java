package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    public List<Course> findAllCourses(){
        return DataHolder.courses;
    }

    public Course findById(Long courseId){
        return DataHolder.courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId){

        Course course = findById(courseId);

        if(course == null){
            return null;
        }
        return course.getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        List<Student> students = findAllStudentsByCourse(course.getCourseId());
        students.removeIf(s -> s.getUsername().equals(student.getUsername()));
        course.getStudents().add(student);
        return course;
    }

    public Course saveOrUpdate(Course course){
        DataHolder.courses.removeIf(i -> i.getCourseId().equals(course.getCourseId()));
        DataHolder.courses.add(course);
        return course;
    }

    public void deleteById(Long id){
        DataHolder.courses.removeIf(i -> i.getCourseId().equals(id));
    }

    public Optional<Course> findByName(String name){
        return DataHolder.courses.stream().filter(i -> i.getName().equals(name)).findFirst();
    }
}
