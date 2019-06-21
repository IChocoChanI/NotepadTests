import io.github.bonigarcia.wdm.WebDriverManager;
import objects.LoginPage;
import objects.NotePad;
import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AnotepadTest {

    private static final String TITLE = "My new note";
    private static final String NOTE = "Sample note";
    private static final String EMAIL = "test@zxcv.com";
    private static final String PASSWORD = "password";

    WebDriver driver;
    NotePad np;
    LoginPage lp;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        np = new NotePad(driver);
        lp = new LoginPage(driver);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    @DisplayName("Create a note")
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

    @Test
    @DisplayName("Create and login to the account")
    public void testLogin(){
        lp
                .open()
                .registerEmail(EMAIL)
                .registerPassword(PASSWORD)
                .createAccount()
                .enterEmail(EMAIL)
                .enterPassword(PASSWORD)
                .login();

        Assert.assertEquals("Logout", lp.getLogoutButton());
    }
}
