package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    private HtmlUnitDriver driver;
    private static boolean dataInitialized = false;
    private static Teacher t1;
    private static Teacher t2;
    private static Course c1;
    private static Course c2;
    private static String username = "admin";
    private static String password = "admin";

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {
            t1 = teacherService.save("Ana","Todorovska");
            t2 = teacherService.save("Kostadin","Mishev");

            c1 = courseService.saveCourse("Course 1","desc", t1.getId(), Type.ELECTIVE,true);
            c1 = courseService.saveCourse("Course 2","desc", t2.getId(), Type.MANDATORY,true);

            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() {
        CoursePage coursePage = CoursePage.to(this.driver);
        coursePage.assertElements(0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        coursePage = LoginPage.doLogin(driver, loginPage, username, password);
        coursePage.assertElements(2, 2, 1);
    }

}
