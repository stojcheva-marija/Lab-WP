package mk.ukim.finki.wp.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController{

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GradeRepository gradeRepository;

    @GetMapping
    public String getCoursesPage(HttpServletRequest req, @RequestParam(required = false) String error, Model model){
        if(req.getSession() != null)
            req.getSession().invalidate();

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }

        model.addAttribute("courses",courseService.listAll());
        return "listCourses";
    }

    @PostMapping("/fullSearch")
    public String doSearch(HttpServletRequest req, HttpServletResponse resp,Model model){
        String search = req.getParameter("searchValue");
        List<Student> searchedStudents = studentService.searchByNameOrSurname(search);
        List<Course> searchedCourses = courseService.searchByCourseNameorDescription(search);
        if (searchedCourses.size() > 0)
            model.addAttribute("searchedCourses",searchedCourses);
        if(searchedStudents.size() > 0)
            model.addAttribute("searchedStudents",searchedStudents);

        return "search";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @PostMapping
    public String nextPage(HttpServletRequest req, Model model){
        try{
            Long courseId = Long.parseLong(req.getParameter("courseId"));
            req.getSession().setAttribute("courseId",courseId);
            return "redirect:/AddStudent";
        }
        catch (NumberFormatException e){
            return "redirect:/courses?error=Please select a course to continue";
        }
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description,
                             @RequestParam Long id, @RequestParam Type type,
                             @RequestParam (required = false) String courseExisists){
        try {
            boolean save = true;
            if(courseExisists == null)
                save=false;
            this.courseService.saveCourse(name, description, id,type,save);
        }
        catch (CourseAlreadyExistsException exception){
            return "redirect:/courses?error=" + exception.getMessage();
        }
        return "redirect:/courses";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping ("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        List<Grade> c = gradeRepository.findByCourse_CourseId(id);
        c.stream().forEach(g -> gradeRepository.deleteById(g.getId()));
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        Course course = this.courseService.getCourseById(id);
        if(course == null){
           return "redirect:/courses?error=Course for editing not found";
        }

        model.addAttribute("course",course);
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("types", Arrays.stream(Type.values()).map(Enum::name).collect(Collectors.toList()));
        return "add-course";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String getAddCoursePage(Model model){
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("types", Arrays.stream(Type.values()).map(Enum::name).collect(Collectors.toList()));
        return "add-course";
    }

}
