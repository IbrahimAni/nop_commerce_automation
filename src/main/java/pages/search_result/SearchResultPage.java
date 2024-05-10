package pages.search_result;

import base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {
    WebDriver driver;
    Logger logger = Logger.getLogger(SearchResultPage.class);

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(xpath = "//h1[normalize-space()='Search']")
    private WebElement searchResultTitle;

    @FindBy(xpath = "//h2[@class='product-title']")
    private List<WebElement> searchResultItems;

    //Methods
    public void searchResultTitleIsVisible(){
        waitForElementToBeVisible(driver, searchResultTitle);
        logger.info("Search Result Title is visible");
    }

    public List<String> getSearchResultItems(){
        List<String> items = new ArrayList<>();
        for(WebElement item : searchResultItems){
            items.add(item.getText());
        }
        logger.info("Search result items are visible");
        return items;
    }

    //Elements
}
