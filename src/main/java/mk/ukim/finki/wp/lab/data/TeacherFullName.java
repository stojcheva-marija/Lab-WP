package mk.ukim.finki.wp.lab.data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TeacherFullName implements Serializable {
    String name;
    String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public TeacherFullName() {
    }

    public TeacherFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
