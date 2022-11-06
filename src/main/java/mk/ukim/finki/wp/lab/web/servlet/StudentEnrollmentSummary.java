package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
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

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
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
        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

}
