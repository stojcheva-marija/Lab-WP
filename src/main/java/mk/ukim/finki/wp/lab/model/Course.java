package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
    //@Enumerated(EnumType.STRING)
    private Type courseType;

    public Course(String name, String description, List<Student> students) {
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    public Course(String name, String description, Teacher teacher){
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
    }

    public Course(String name, String description, Teacher teacher, Type courseType) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.courseType = courseType;
    }
}
