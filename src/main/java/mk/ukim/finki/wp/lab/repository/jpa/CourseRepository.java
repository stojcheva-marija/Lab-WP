package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByName(String name);
    @Query(value = "select c from Course c join Teacher t on c.teacher.id = t.id where c.name like %:text% or c.description like %:text%" +
            " or t.fullName.name like %:text% or t.fullName.surname like %:text%")
    List<Course> findCourseByNameDescriptionTeacher(String text);
}
