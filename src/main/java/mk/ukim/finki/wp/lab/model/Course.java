package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    public Course(String name, String description, List<Student> students) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
        this.courseId = (long) (Math.random()*1000);
    }

    public Course(String name, String description, Teacher teacher){
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
        this.courseId = (long) (Math.random()*1000);
    }
}
