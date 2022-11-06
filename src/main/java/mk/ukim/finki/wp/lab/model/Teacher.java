package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Teacher {
    private Long id;
    private String name;
    private String surname;

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = (long) (Math.random()*1000);
    }
}
