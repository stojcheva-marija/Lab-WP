package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaveStudentTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        Student s = new Student("username", "password", "name", "surname");
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(s);
        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository));
    }

    @Test
    public void testSuccessRegister() {
        Student s = this.studentService.save("username",
                "password",
                "name",
                "surname");

        Mockito.verify(this.studentService).save("username", "password", "name", "surname");

        Assert.assertNotNull("Student is null", s);
        Assert.assertEquals("Name do not mach", "name", s.getName());
        Assert.assertEquals("Surnames do not mach", "surname", s.getSurname());
        Assert.assertEquals("Passwords do not mach", "password", s.getPassword());
        Assert.assertEquals("Usernames do not mach", "username", s.getUsername());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save(null, "password", "name", "surname"));
        Mockito.verify(this.studentService).save(null, "password","name", "surname");
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save(username, "password", "name", "surname"));
        Mockito.verify(this.studentService).save(username, "password","name", "surname");
    }


    @Test
    public void testEmptyPassword() {
        String password = "";
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", password, "name", "surname"));
        Mockito.verify(this.studentService).save("username",password,"name", "surname");
    }

    @Test
    public void testNullPassword() {
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", null, "name", "surname"));
        Mockito.verify(this.studentService).save("username",null,"name", "surname");
    }

    @Test
    public void testEmptyName() {
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password",  "", "surename"));
        Mockito.verify(this.studentService).save("username", "password",  "", "surename");
    }

    @Test
    public void testNullName() {
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password",  null, "surename"));
        Mockito.verify(this.studentService).save("username", "password",  null, "surename");
    }

    @Test
    public void testEmptySurname() {
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password",  "name", ""));
        Mockito.verify(this.studentService).save("username", "password",  "name", "");
    }

    @Test
    public void testNullSurname() {
        Assert.assertThrows("IllegalArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password",  "name", null));
        Mockito.verify(this.studentService).save("username", "password",  "name", null);
    }

    @Test
    public void testDuplicateUsername() {
        Student student = new Student("username", "password", "name", "surname");
        Mockito.when(this.studentRepository.findByUsername(Mockito.anyString())).thenReturn(student);
        String username = "username";
        Assert.assertThrows("IllegalStateException expected",
                IllegalStateException.class,
                () -> this.studentService.save(username, "password", "name","surname"));
        Mockito.verify(this.studentService).save(username, "password", "name", "surname");
    }
}
