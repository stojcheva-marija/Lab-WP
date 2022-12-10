package mk.ukim.finki.wp.lab.web.controller;


import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/addGrade")
public class AddGradeController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final GradeService gradeService;

    @GetMapping
    public String doGet(@SessionAttribute Long courseId, Model model){
        model.addAttribute("students",courseService.getCourseById(courseId).getStudents());
        System.out.println(courseService.getCourseById(courseId).getStudents());
        return "addGrade";
    }

    @PostMapping
    public String doPost(@RequestParam
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
    @SessionAttribute Long courseId,
                         @RequestParam String student,
                         @RequestParam String grade) {
       gradeService.save(studentService.findByUsername(student),
               courseService.getCourseById(courseId),
               grade.charAt(0),
               dateTime);
        return "redirect:/StudentEnrollmentSummary";
    }
}

