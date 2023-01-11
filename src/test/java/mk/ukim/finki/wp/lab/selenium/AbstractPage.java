package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AbstractPage {
    @FindBy(className = "delete-course")
    private List<WebElement> deleteButtons;

    @FindBy(className = "edit-course")
    private List<WebElement> editButtons;

    @FindBy(className = "add-course")
    private List<WebElement> addCourseButton;
    @FindBy(className = "select-course")
    private List<WebElement> selectButtons;
    @FindBy(className = "submit")
    private WebElement submitButton;

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }
}
