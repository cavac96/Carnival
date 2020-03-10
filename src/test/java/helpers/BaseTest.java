import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    private static WebDriver webDriver;
    protected static HomePage homePage;

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.carnival.com");
    }
    @After
    public void tearDown() {
        webDriver.quit();
    }
}