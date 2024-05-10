package pages.logout;

import base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BasePage {
    WebDriver driver;
    Logger logger = Logger.getLogger(LogoutPage.class);

    public LogoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators

    //Methods

    //Elements
}
