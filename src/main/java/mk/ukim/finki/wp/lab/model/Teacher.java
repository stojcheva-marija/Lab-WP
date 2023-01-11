package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.data.TeacherFullName;
import mk.ukim.finki.wp.lab.data.TeacherFullNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Convert(converter = TeacherFullNameConverter.class)
    @Embedded
    private TeacherFullName fullName;

    private LocalDate dateOfEmployment;

    public Teacher(TeacherFullName teacherFullName) {
        this.fullName = teacherFullName;
    }
}
