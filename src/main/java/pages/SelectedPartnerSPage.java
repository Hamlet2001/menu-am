package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class SelectedPartnerSPage extends BasePage {
    public SelectedPartnerSPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Ավելացնել զամբյուղ']")
    private WebElement buttonToAddToCart;
    @FindBy(xpath = "//img[@data-id='image']")
    private List<WebElement> listOfFoods;
    private final String xpathForPreferredFood = "//h3[text()='%s']";
    private final String xpathForAmount = "//h3[text()='%s']/parent::div/parent::div//span[@data-id='amount']";

    public SelectedPartnerSPage chooseFood(String preferredFood) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .elementToBeClickable(By.xpath(String.format(xpathForPreferredFood, preferredFood))));
        try {
            driver.findElement(By.xpath(String.format(xpathForPreferredFood, preferredFood))).click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(driver.findElement(By.xpath(String.format(xpathForPreferredFood, preferredFood))));
        }
        return this;
    }

    public void addToCart() {
        buttonToAddToCart.click();
    }

    public int getSelectedFoodCost(String preferredFood) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .elementToBeClickable(By.xpath(String.format(xpathForPreferredFood, preferredFood))));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(String.format(xpathForAmount, preferredFood))));
        return Integer.parseInt(driver.findElement(By.xpath(String.format(xpathForAmount, preferredFood))).getText());
    }

    public SelectedPartnerSPage waitForSelectedPartnersPageLoaded() {
        new WebDriverWait(driver, ofSeconds(30)).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//img[@data-id='image']"), 0));
        new WebDriverWait(driver, ofSeconds(30)).
                until(ExpectedConditions.elementToBeClickable(listOfFoods.get(listOfFoods.size() - 1)));
        return this;
    }
}
