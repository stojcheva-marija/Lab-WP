package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursePage extends AbstractPage{
    @FindBy(className = "delete-course")
    private List<WebElement> deleteButtons;

    @FindBy(className = "edit-course")
    private List<WebElement> editButtons;

    @FindBy(className = "add-course")
    private List<WebElement> addCourseButton;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public static CoursePage to(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursePage.class);
    }

    public void assertElements(int deleteButtons, int editButtons, int addCourseButton) {
        Assert.assertEquals("Number of delete buttons do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("Number of edit buttons do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("Add button visibility is not correct", addCourseButton, this.getAddCourseButton().size());
    }

}
