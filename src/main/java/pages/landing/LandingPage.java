package pages.landing;

import base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends BasePage {
    private final WebDriver driver;
    Logger logger = Logger.getLogger(LandingPage.class);

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(xpath = "//select[@id='customerCurrency']")
    private WebElement currencyDropdown;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Log in")
    private WebElement loginLink;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[@class='ico-wishlist']")
    private WebElement wishlistLink;

    @FindBy(xpath = "//li[@id='topcartlink']")
    private WebElement shoppingCartLink;

    @FindBy(xpath = "//img[@alt='nopCommerce demo store']")
    private WebElement logo;

    @FindBy(xpath = "//input[@id='small-searchterms']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//div[@class='prices']")
    private List<WebElement> featuredProductPrices;

    //Methods
    public void clickRegisterLink() {
        waitForElementToBeClickable(driver, registerLink);
        clickElement(registerLink);
        logger.info("Register link clicked");
    }

    public void clickLoginLink() {
        waitForElementToBeClickable(driver, loginLink);
        clickElement(loginLink);
        logger.info("Login link clicked");
    }

    public String getLoginLinkText() {
        waitForElementToBeVisible(driver, loginLink);
        return getElementText(loginLink);
    }

    public void clickLogoutLink() {
        waitForElementToBeClickable(driver, logoutLink);
        clickElement(logoutLink);
        logger.info("Logout link clicked");
    }

    public String getLogoutLinkText() {
        waitForElementToBeVisible(driver, logoutLink);
        return getElementText(logoutLink);
    }

    public void selectCurrency(String currency) {
        waitForElementToBeClickable(driver, currencyDropdown);
        selectFromDropdownByVisibleText(currencyDropdown, currency);
        logger.info("Currency selected");
    }

    public void waitForPricesCurrencyToChange(String currency) {
        try {
            Thread.sleep(5000);
            for (WebElement price : featuredProductPrices) {
                waitForElementToBeVisible(driver, price);
                scrollToElement(driver, price);
                logger.info("Price of featured product in '" + currency + "': " + getElementText(price));
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getPriceOfFeaturedProduct(){
        List<String> prices = new ArrayList<>();
        try {
            Thread.sleep(3000);
            for (WebElement price : featuredProductPrices) {
                waitForElementToBeVisible(driver, price);
                scrollToElement(driver, price);
                prices.add(getElementText(price));
            }
            logger.info("Prices of featured products: " + prices);

            return prices;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
       return null;
    }

    public void enterSearchText(String searchItem) {
        waitForElementToBeVisible(driver, searchBox);
        clearInputField(searchBox);
        enterText(searchBox, searchItem);
        logger.info("Search text entered");
    }

    public void clickSearchBtn() {
        waitForElementToBeClickable(driver, searchBtn);
        clickElement(searchBtn);
        logger.info("Search button clicked");
    }


    //Elements
}
