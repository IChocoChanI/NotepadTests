import io.github.bonigarcia.wdm.WebDriverManager;
import objects.NotePad;
import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AnotepadTest {

    private static final String TITLE = "My new note";
    private static final String NOTE = "Sample note";

    WebDriver driver;
    NotePad np;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        np = new NotePad(driver);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void testCreatingDeletingNote() {
        np
                .open()
                .setTitle(TITLE)
                .save();

        assertEquals(TITLE, np.getTitleContent());
    }

    @Test
    @DisplayName("GL-523:F-50: Saving Private or Public notes by registered users")
    public void testPrivateNote() {
        np
                .open()
                .setTitle(TITLE)
                .addContent(NOTE)
                .save()
                .setNoteReadPermission()
                .closePermissionPopup();

        assertEquals("Private Note", np.getPrivateNoteBtnName());
    }
}
