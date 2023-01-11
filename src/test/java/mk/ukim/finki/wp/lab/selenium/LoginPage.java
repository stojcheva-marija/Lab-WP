package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

    private WebElement username;
    private WebElement password;
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage openLogin(WebDriver driver) {
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static CoursePage doLogin(WebDriver driver, LoginPage page, String username, String password) {
        page.username.sendKeys(username);
        page.password.sendKeys(password);
        page.submit.click();
        return PageFactory.initElements(driver, CoursePage.class);
    }
}
