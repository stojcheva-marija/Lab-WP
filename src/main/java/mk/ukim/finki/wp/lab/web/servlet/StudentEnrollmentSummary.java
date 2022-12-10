package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentEnrollmentSummary", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final GradeService gradeService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        Long courseId = (Long) req.getSession().getAttribute("courseId");
        String username = req.getParameter("size");
        courseService.addStudentInCourse(username, courseId);
        resp.sendRedirect("/StudentEnrollmentSummary");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long courseId = (Long) req.getSession().getAttribute("courseId");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("course", courseService.getCourseById(courseId));
        context.setVariable("grades",gradeService.getAllGradesFromCourse(courseId));
        context.setVariable("students",courseService.getCourseById(courseId).getStudents());
        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }


}
