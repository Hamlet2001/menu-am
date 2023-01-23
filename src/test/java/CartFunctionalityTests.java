import initDriver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CartFunctionalityTests extends BaseTest {
    private final String partnerName = "Պիցցա Միցցա";
    private final String concreteTypeOfFood = "Պիցցա Կեսար";
    private final String filePath = "C:\\Users\\Hamlet\\Desktop\\menu-am\\.properties";

    public void addProductFromFiltersToCart() {
        String preferredFoodType = "Պիցցա";
        new LoginPage(DriverFactory.getDriver())
                .chooseFoodType(preferredFoodType)
                .waitForPartnersLoaded()
                .choosePartner(partnerName)
                .waitForSelectedPartnersPageLoaded()
                .chooseFood(concreteTypeOfFood)
                .addToCart();
    }

    @Test
    public void TestOne() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        String login = properties.getProperty("login3");
        String password = properties.getProperty("password3");
        new HomePage(DriverFactory.getDriver())
                .openHomePage()
                .signIn(login, password)
                .waitForLoginPageLoaded()
                .setDeliveryAddress();
        addProductFromFiltersToCart();
        CartPage cartPage = new CartPage(DriverFactory.getDriver())
                .openCart()
                .waitForCartPageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCountOfItemsInTheCart(), "1");
        softAssert.assertAll();
    }

    @Test
    public void TestTwo() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        String login = properties.getProperty("login3");
        String password = properties.getProperty("password3");
        new HomePage(DriverFactory.getDriver())
                .openHomePage()
                .signIn(login, password)
                .waitForLoginPageLoaded()
                .setDeliveryAddress();
        addProductFromFiltersToCart();
        CartPage cartPage = new CartPage(DriverFactory.getDriver())
                .openCart()
                .waitForCartPageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getPartnerName(), partnerName);
        softAssert.assertAll();
    }

    @Test
    public void TestThree() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        String login = properties.getProperty("login2");
        String password = properties.getProperty("password2");
        new HomePage(DriverFactory.getDriver())
                .openHomePage()
                .waitForHomePageLoaded()
                .signIn(login, password)
                .waitForLoginPageLoaded()
                .setDeliveryAddress();
        addProductFromFiltersToCart();
        CartPage cartPage = new CartPage(DriverFactory.getDriver())
                .openCart()
                .waitForCartPageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getTheActualFullCost(), cartPage.getTheExpectedFullCost());
        softAssert.assertAll();
    }

    @Test
    public void TestFour() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        String login = properties.getProperty("login2");
        String password = properties.getProperty("password2");
        new HomePage(DriverFactory.getDriver())
                .openHomePage()
                .signIn(login, password)
                .waitForLoginPageLoaded()
                .setDeliveryAddress();
        addProductFromFiltersToCart();
        SelectedPartnerSPage selectedPartnerSPage = new SelectedPartnerSPage(DriverFactory.getDriver());
        int costOfFoodFromFoodPage = selectedPartnerSPage.getSelectedFoodCost(concreteTypeOfFood);
        CartPage cartPage = new CartPage(DriverFactory.getDriver())
                .openCart()
                .waitForCartPageLoaded();
        int costOfFoodFromCartPage = cartPage.getTheCostOfFood();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(costOfFoodFromFoodPage, costOfFoodFromCartPage);
        softAssert.assertAll();
    }

    @Test
    public void TestFive() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        String login = properties.getProperty("login1");
        String password = properties.getProperty("password1");
        new HomePage(DriverFactory.getDriver())
                .openHomePage()
                .signIn(login, password)
                .waitForLoginPageLoaded()
                .setDeliveryAddress();
        addProductFromFiltersToCart();
        new SelectedPartnerSPage(DriverFactory.getDriver())
                .waitForSelectedPartnersPageLoaded()
                .chooseFood(concreteTypeOfFood)
                .addToCart();
        FilteredFoodPage filteredFoodPage = new FilteredFoodPage(DriverFactory.getDriver());
        int shippingCostFromFilteredFoodPage = filteredFoodPage.getShippingCost(partnerName);
        CartPage cartPage = new CartPage(DriverFactory.getDriver())
                .openCart()
                .waitForCartPageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCountOfItemsInTheCart(), "2");
        softAssert.assertEquals(shippingCostFromFilteredFoodPage, cartPage.getShippingCost());
        softAssert.assertAll();
    }

    @Test
    public void TestSix() throws IOException, InterruptedException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        String login = properties.getProperty("login1");
        String password = properties.getProperty("password1");
        new HomePage(DriverFactory.getDriver())
                .openHomePage()
                .signIn(login, password)
                .waitForLoginPageLoaded()
                .setDeliveryAddress();
        String concreteProductNameFromDiscountsPage = "Ընտանեկան կոմբո";
        new LoginPage(DriverFactory.getDriver())
                .clickOnDiscountsButton()
                .waitForDiscountsPageLoaded()
                .addAConcreteProductToTheCart(concreteProductNameFromDiscountsPage);
        CartPage cartPage = new CartPage(DriverFactory.getDriver())
                .openCart();
        Thread.sleep(6000);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCountOfItemsInTheCart(), "1");
        softAssert.assertEquals(cartPage.getTheNameOfTheProductFromTheCart(), concreteProductNameFromDiscountsPage);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void clearCartAndLogOut() {
        new CartPage(DriverFactory.getDriver())
                .clearCart()
                .logout();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
