package pages;

import initDriver.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    protected final String HOME_URL = "https://menu.am";

    public HomePage openHomePage() {
        driver.manage().window().maximize();
        driver.get(HOME_URL);
        return this;
    }

    @FindBy(xpath = "//div[@role='dialog']")
    private WebElement advertisement;
    @FindBy(xpath = "//span[text() = 'Լավ']")
    private WebElement buttonForCloseAdvertisement;
    @FindBy(css = "input[name='user']")
    private WebElement inputForEmail;
    @FindBy(css = "input[name='pwd']")
    private WebElement inputForPassword;
    @FindBy(xpath = "//span[text()='Մուտք']")
    private WebElement loginButton;
    @FindBy(xpath = "//span[text()='Ցույց տալ ավելին']")
    private WebElement showMoreButton;

    public LoginPage signIn(String login, String password) {
        try {
            if (advertisement.isDisplayed()) {
                new WebDriverWait(driver, Duration.ofSeconds(20)).
                        until(ExpectedConditions.elementToBeClickable(buttonForCloseAdvertisement));
                buttonForCloseAdvertisement.click();
            }
        } catch (Exception ignored) {
        }
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(loginButton);
        }
        inputForEmail.sendKeys(login);
        inputForPassword.sendKeys(password, Keys.ENTER);
        return new LoginPage(DriverFactory.getDriver());
    }

    public HomePage waitForHomePageLoaded() {
        try {
            if (advertisement.isDisplayed()) {
                new WebDriverWait(driver, Duration.ofSeconds(20)).
                        until(ExpectedConditions.elementToBeClickable(buttonForCloseAdvertisement));
                buttonForCloseAdvertisement.click();
            }
        } catch (NoSuchElementException ignored) {
        }
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.
                elementToBeClickable(showMoreButton));
        return this;
    }
}
