package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AllPages extends BasePage{
    WebDriver driver;
    Logger logger = Logger.getLogger(AllPages.class);

    public AllPages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Return Elements

}
