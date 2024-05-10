package pages.login;

import base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    WebDriver driver;
    Logger logger = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(xpath = "//div[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    private WebElement loginBtn;

    //Methods
    public void pageTitleIsVisible(){
        waitForElementToBeVisible(driver, pageTitle);
        logger.info("Login Page Title is visible");
    }

    public void enterEmail(String email){
        waitForElementToBeVisible(driver, emailField);
        enterText(emailField, email);
        logger.info("Email entered");
    }

    public void enterPassword(String password){
        waitForElementToBeVisible(driver, passwordField);
        enterText(passwordField, password);
        logger.info("Password entered");
    }

    public void clickLoginBtn(){
        waitForElementToBeClickable(driver, loginBtn);
        clickElement(loginBtn);
        logger.info("Login button clicked");
    }

    //Elements
}
