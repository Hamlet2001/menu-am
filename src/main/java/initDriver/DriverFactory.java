package initDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private static void initDriver(BrowserType browser) {
        WebDriver driver;
        switch (browser) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case SAFARI -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }
        DRIVER_THREAD_LOCAL.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER_THREAD_LOCAL.get();
        if (driver != null) return driver;
        initDriver(BrowserType.valueOf(System.getProperty("browser").toUpperCase()));
        return getDriver();
    }

    public static void quitDriver() {
        DRIVER_THREAD_LOCAL.get().quit();
        DRIVER_THREAD_LOCAL.remove();
    }

}
