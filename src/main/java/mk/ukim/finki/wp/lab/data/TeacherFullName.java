package mk.ukim.finki.wp.lab.data;

import java.io.Serializable;

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
}
