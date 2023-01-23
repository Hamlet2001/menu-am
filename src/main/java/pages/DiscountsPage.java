package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;

public class DiscountsPage extends BasePage {
    public DiscountsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@data-id='product']")
    private List<WebElement> listOfOffers;
    private final String xpathForConcreteProductToAddToCart =
            "//h3[text()='%s']//parent::div/following-sibling::div/div[@data-id='add-to-cart']";

    public void addAConcreteProductToTheCart(String concreteProductFromDiscountsPage) {
        new WebDriverWait(driver, ofSeconds(20)).until(ExpectedConditions.
                elementToBeClickable(By.xpath(String.format(xpathForConcreteProductToAddToCart, concreteProductFromDiscountsPage))));
        driver.findElement(By.xpath(String.format(xpathForConcreteProductToAddToCart, concreteProductFromDiscountsPage))).click();
    }

    public DiscountsPage waitForDiscountsPageLoaded() {
        new WebDriverWait(driver, ofSeconds(30)).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@data-id='product']"), 0));
        new WebDriverWait(driver, ofSeconds(30)).
                until(ExpectedConditions.elementToBeClickable(listOfOffers.get(listOfOffers.size() - 1)));
        return this;
    }
}
