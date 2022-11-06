package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/courses")
public class CourseController{

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

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
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long id, Model model){
        try {
            this.courseService.saveCourse(name, description, id);
        }
        catch (RuntimeException exception){
            return "redirect:/courses?error=" + exception.getMessage();
        }
        return "redirect:/courses";
    }

    @GetMapping ("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        Course course =this.courseService.getCourseById(id);
        if(course == null){
           return "redirect:/courses?error=Course for editing not found";
        }

        model.addAttribute("course",course);
        model.addAttribute("teachers", teacherService.findAll());
        return "add-course";
    }

    @GetMapping("/add-form")
    public String getAddCoursePage(Model model){
        model.addAttribute("teachers", teacherService.findAll());
        return "add-course";
    }

}
