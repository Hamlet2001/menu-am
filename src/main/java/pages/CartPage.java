package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@aria-label='basket']")
    private WebElement cartButton;
    @FindBy(xpath = "//span[@data-id='cost']")
    private WebElement spanForCost;
    @FindBy(xpath = "//span[contains(@class, 'count')]/span")
    private WebElement spanForCountOfItemsInCart;
    @FindBy(xpath = "//span[@data-id='partnerDelivery']")
    private WebElement spanForDeliveryCost;
    @FindBy(xpath = "//*[@id='content']/div/div[3]/div[3]/div[2]/div")
    private WebElement spanForFullCost;
    @FindBy(xpath = "//div[@data-id='partnerName']")
    private WebElement divForPartner;
    @FindBy(xpath = "//a[@data-id='deleteAll']")
    private WebElement clearAllButton;
    @FindBy(xpath = "//span[text()='Դատարկել']")
    private WebElement buttonToEmpty;
    @FindBy(xpath = "//div[@data-id='quantity']/parent::div/parent::div/preceding-sibling::div/div[1]")
    private WebElement divForProductNameInCart;
    @FindBy(xpath = "//span[text()='Պատվիրել հիմա']")
    private WebElement orderNowButton;
    @FindBy(xpath = "//button[@aria-label='account control']")
    private WebElement accountButton;
    @FindBy(xpath = "//button[text()='Ելք']")
    private WebElement buttonForLogout;
    @FindBy(xpath = "//span[text()='Մուտք']")
    private WebElement loginButton;

    public CartPage openCart() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                    .elementToBeClickable(cartButton));
            cartButton.click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(cartButton);
        }
        return this;
    }

    public String getCountOfItemsInTheCart() {
        new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions
                .visibilityOf(spanForCountOfItemsInCart));
        WebElement el = driver.findElement(By.xpath("//div[@data-id='quantity']//input[@type='text']"));
        String count = el.getAttribute("value");
        return count;
    }

    public int getShippingCost() {
        new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOf(spanForDeliveryCost));
        return Integer.parseInt(spanForDeliveryCost.getText());
    }

    public int getTheCostOfFood() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .visibilityOf(spanForCost));
        return Integer.parseInt(spanForCost.getText());
    }

    public int getTheActualFullCost() {
        return getShippingCost() + getTheCostOfFood();
    }

    public String getPartnerName() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(divForPartner));
        return divForPartner.getText();
    }

    public CartPage clearCart() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                    .elementToBeClickable(clearAllButton));
            clearAllButton.click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(clearAllButton);
        }
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                    .elementToBeClickable(buttonToEmpty));
            buttonToEmpty.click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(buttonToEmpty);
        }
        return this;
    }

    public int getTheExpectedFullCost() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .visibilityOf(spanForFullCost));
        return Integer.parseInt(spanForFullCost.getText());
    }

    public String getTheNameOfTheProductFromTheCart() {
        return divForProductNameInCart.getText();
    }

    public CartPage waitForCartPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(clearAllButton));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(orderNowButton));
        return this;
    }

    public void logout() {
        try {
            new WebDriverWait(driver, ofSeconds(20)).until(ExpectedConditions
                    .elementToBeClickable(accountButton));
            accountButton.click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(accountButton);
        }
        try {
            new WebDriverWait(driver, ofSeconds(20)).until(ExpectedConditions
                    .elementToBeClickable(buttonForLogout));
            buttonForLogout.click();
        } catch (Exception e) {
            clickByJavaScriptExecutor(buttonForLogout);
        }
        new WebDriverWait(driver, ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}


