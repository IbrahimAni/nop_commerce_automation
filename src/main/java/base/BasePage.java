package base;

import constants.StringConstant;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    StringConstant stringConstant = new StringConstant();

    public BasePage() {
        PropertyConfigurator.configure(stringConstant.log4jFilePath());
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PropertyConfigurator.configure(stringConstant.log4jFilePath());
    }

    @FindBy(xpath = "//a")
    List<WebElement> element;

    @FindBy(xpath = "//img")
    List<WebElement> imgElement;

    public List<WebElement> clickableLinks() {
        List<WebElement> linksToClick = new ArrayList<>();
        if(element != null){
            element.addAll(imgElement);
        }

        for (WebElement e : element) {
            if (e.getAttribute("href") != null) {
                linksToClick.add(e);
            }
        }

        return linksToClick;
    }

    public String linkStatus(URI uri) {
        try{
            URL url = uri.toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setConnectTimeout(5000);
            http.connect();
            String responseMessage = http.getResponseMessage();
            http.disconnect();
            return responseMessage;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void clickElement(WebElement element) {
        element.click();
    }

    protected void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected String getElementText(WebElement element) {
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try{
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    protected boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    protected void clearInputField(WebElement element) {
        element.clear();
    }

    protected String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    protected static void submit(WebElement element){
        element.submit();
    }

    protected void clickUsingJS(WebElement element, WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void scrollToElementJs(WebElement element, WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElement(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    protected void dragAndDrop(WebElement source, WebElement target, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).build().perform();
    }

    protected void clickAndHold(WebElement element, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).build().perform();
    }

    protected void release(WebElement element, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.release(element).build().perform();
    }

    protected void rightClick(WebElement element, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.contextClick(element).build().perform();
    }

    protected void doubleClick(WebElement element, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
    }

    protected void switchToFrame(WebElement element, WebDriver driver){
        driver.switchTo().frame(element);
    }

    protected void switchBackFromFrame(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    protected void refresh(WebDriver driver){
        driver.navigate().refresh();
    }

    public void goBack(WebDriver driver){
        driver.navigate().back();
    }

    protected void goForward(WebDriver driver){
        driver.navigate().forward();
    }

    protected void waitForElementToBeClickable(WebDriver driver, WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }

    public void waitForElementToBeVisible(WebDriver driver, WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected void waitForElementToContainText(WebDriver driver, WebElement element, String text){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
    }

    public void waitForTwoSeconds(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());;
        }
    }

    protected void rightClickJs(WebElement element, WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].contextmenu();", element);
    }

    protected void doubleClickJs(WebElement element, WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].dblclick();", element);
    }

    protected void uploadFile(WebElement element, String path){
        element.sendKeys(path);
    }

    protected void selectFromDropdownByVisibleText(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text.equalsIgnoreCase("random") ? "Random" : text);
    }

    protected void selectFromDropdownByIndex(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
}
