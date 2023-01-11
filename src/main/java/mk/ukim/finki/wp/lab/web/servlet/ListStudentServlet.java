//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.service.StudentService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name="ListStudentServlet", urlPatterns = "/AddStudent")
//public class ListStudentServlet extends HttpServlet {
//
//    private final StudentService studentService;
//    private final SpringTemplateEngine springTemplateEngine;
//    private final CourseService courseService;
//
//    public ListStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine,CourseService courseService) {
//        this.studentService = studentService;
//        this.springTemplateEngine = springTemplateEngine;
//        this.courseService = courseService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        Long courseId = (Long) req.getSession().getAttribute("courseId");
//        context.setVariable("course", courseService.getCourseById(courseId));
//        context.setVariable("students", studentService.listAll());
//        springTemplateEngine.process("listStudents.html", context, resp.getWriter());
//    }
//
//}
