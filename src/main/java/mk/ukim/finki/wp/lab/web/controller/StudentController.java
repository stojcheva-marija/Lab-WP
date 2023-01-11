package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class StudentController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;

    public StudentController(CourseService courseService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }
    @GetMapping("/AddStudent")
    public String getListStudentsPage(@SessionAttribute Long courseId,
            Model model){
        model.addAttribute("course",courseService.getCourseById(courseId));
        model.addAttribute("students",studentService.listAll());
        return "listStudents";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/CreateStudent")
    public String getCreateStudentPage(){
        return "createStudent";
    }

    @PostMapping("/CreateStudent")
    public String createStudent(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String name,
                                 @RequestParam String surname){
        studentService.save(username, password, name, surname);
        return "redirect:/AddStudent";
    }

   @PostMapping("/SearchStudent")
    public String searchStudent(@RequestParam String searchValue,
                                @SessionAttribute Long courseId,
                                Model model){
        List<Student> searchedStudents = studentService.searchByNameOrSurname(searchValue);
        model.addAttribute("course",courseService.getCourseById(courseId));
        model.addAttribute("students",searchedStudents);
        return "listStudents";
    }
    @GetMapping("/StudentEnrollmentSummary")
    public String getStudentEnrollmentSummaryPage(@SessionAttribute Long courseId,
                                                  Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        model.addAttribute("grades", gradeService.getAllGradesFromCourse(courseId));
        model.addAttribute("students", courseService.getCourseById(courseId).getStudents());
        return "studentsInCourse";
    }

    @PostMapping("/StudentEnrollmentSummary")
    public String addStudentInCourse(@SessionAttribute Long courseId,
                                                  @RequestParam String size){
        courseService.addStudentInCourse(size, courseId);
        return "redirect:/StudentEnrollmentSummary";


    }
}
