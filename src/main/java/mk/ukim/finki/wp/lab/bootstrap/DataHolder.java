package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Student> students = new ArrayList<Student>();
    public static List<Course> courses = new ArrayList<Course>();
    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("stojcheva.marija","ms","Marija","Stojcheva"));
        students.add(new Student("dodikj.iva","id","Iva","Dodikj"));
        students.add(new Student("maskimovska.ilina","im","Ilina","Maksimovska"));
        students.add(new Student("stojkov.matej","ms","Matej","Stojkov"));
        students.add(new Student("georgievski.bojan","bg","Bojan","Georgievski"));

        courses.add(new Course("Databases",
                "Introducing the student with the basic concepts for using the databases", new ArrayList<Student>()));
        courses.add(new Course("Advanced programming",
                "The student will attain knowledge of generic programming, abstract data types, creation of" +
                        " template classes and functions.",new ArrayList<Student>()));
        courses.add(new Course("Web programming",
                "Web application development, using the MVC pattern.",new ArrayList<Student>()));
        courses.add(new Course("Introduction to Data Science",
                "Students will obtain knowledge with the Data Science fundamentals and they will be introduced to" +
                        " the process and methodologies for operations with data, starting from problem identification, data" +
                        " collection and data processing.",new ArrayList<Student>()));
        courses.add(new Course("Software Design and Architecture",
                "Students should learn the main concepts of the object oriented analysis and design.",new ArrayList<Student>()));

        teachers.add(new Teacher("Riste", "Stojanov"));
        teachers.add(new Teacher("Sasho","Gramatikov"));
        teachers.add(new Teacher("Dimitar","Trajanov"));
        teachers.add(new Teacher("Ana","Todorovska"));
        teachers.add(new Teacher("Kostadin","Mishev"));
    }

}
