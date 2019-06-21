package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage {

    private static final By REGISTER_EMAIL = By.id("registerEmail");
    private static final By REGISTER_PASSWORD = By.id("password");
    private static final By LOGIN_EMAIL = By.id("loginEmail");
    private static final By LOGIN_PASSWORD = By.id("password");
    private static final By CREATE_ACCOUNT = By.id("submit");
    private static final By LOGOUT_BUTTON = By.cssSelector("a[href=\"logout\"]");


    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 3);
    }

    @Step
    public LoginPage open() {
        driver.get("https://anotepad.com/create_account");
        return this;
    }


    @Step
    public LoginPage registerEmail(String email) {
        driver.findElement(REGISTER_EMAIL).sendKeys(email);
        return this;
    }

    @Step
    public LoginPage registerPassword(String password) {
        driver.findElement(REGISTER_PASSWORD).sendKeys(password);
        return this;
    }

    @Step
    public LoginPage createAccount(){
       driver.findElement(CREATE_ACCOUNT).click();
       return this;
    }

    @Step
    public LoginPage enterEmail(String email) {
        driver.findElement(LOGIN_EMAIL).sendKeys(email);
        return this;
    }

    @Step
    public LoginPage enterPassword(String password) {
        driver.findElement(LOGIN_PASSWORD).sendKeys(password);
        List<WebElement> passwords = driver.findElements(LOGIN_PASSWORD);
        if (passwords.size() > 1) {
            passwords.get(1).click();
        }
        return this;
    }

    @Step
    public LoginPage login() {
        driver.findElement(CREATE_ACCOUNT).click();
        return this;
    }

    @Step
    public LoginPage getLogoutButton() {
        driver.findElement(LOGOUT_BUTTON).getAttribute("value");
        return this;
    }
}
