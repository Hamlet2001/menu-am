import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeSuite
    @Parameters("browser")
    public void initTests(String browser) {
        System.setProperty("browser", browser);
    }
}
