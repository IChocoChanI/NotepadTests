package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePad {

    private static final By NOTE_TITLE = By.id("edit_title");
    private static final By NOTE_CONTENT = By.id("edit_textarea");
    private static final By SAVE_TITLE_BUTTON = By.id("btnSaveNote");
    private static final By DELETE_NOTE = By.cssSelector(".delete");
    private static final By NOTE_ACCESS_TEXT = By.id("noteAccessText");
    private static final By ACCESS_PRIVATE = By.id("accessPrivate");
    private static final By END = By.cssSelector(".btn.btn-default");

    private WebDriver driver;
    private WebDriverWait wait;

    public NotePad(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 7);
    }

    @Step
    public NotePad open() {
        driver.get("https://anotepad.com/");
        return this;
    }

    @Step
    public NotePad setTitle(String title) {
        driver.findElement(NOTE_TITLE).sendKeys(title);
        return this;
    }

    @Step
    public NotePad addContent(String content) {
        driver.findElement(NOTE_CONTENT).sendKeys(content);
        return this;
    }

    @Step
    public NotePad save() {
        driver.findElement(SAVE_TITLE_BUTTON).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-warning"), "You have saved your note as a"));
        return this;
    }

    @Step
    public String getNoteContent() {
        return driver.findElement(NOTE_CONTENT).getAttribute("value");
    }

    @Step
    public String getTitleContent() {
        return driver.findElement(NOTE_TITLE).getAttribute("value");
    }

    @Step
    public NotePad setNoteReadPermission() {
        driver.findElement(NOTE_ACCESS_TEXT).click();
        driver.findElement(ACCESS_PRIVATE).click();
        return this;
    }

    @Step
    public NotePad endNoteReadPermission() {
        driver.findElement(END).click();
        return this;
    }

    @Step
    public NotePad getPrivateNoteBtnName() {
        driver.findElement(NOTE_ACCESS_TEXT).getAttribute("value");
        return this;
    }
}

