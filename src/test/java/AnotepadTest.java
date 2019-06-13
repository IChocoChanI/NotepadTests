import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AnotepadTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 7);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void testCreatingDeletingNote() {
        driver.get("https://anotepad.com/");

        driver.findElement(By.id("edit_title")).sendKeys("My New Note");
        driver.findElement(By.id("btnSaveNote")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-warning"), "You have saved your note as"));

        driver.findElement(By.cssSelector(".delete")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        alert.accept();

        assertEquals("No note here.", driver.findElement(By.cssSelector(".saved_notes div")).getText());

        driver.findElement(By.cssSelector(".saved_notes div")).getText();
    }
}
