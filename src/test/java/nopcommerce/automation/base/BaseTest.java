package nopcommerce.automation.base;

import base.AllPages;
import constants.Browser;
import constants.StringConstant;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.landing.LandingPage;
import pages.login.LoginPage;
import pages.search_result.SearchResultPage;
import utils.BrowserFactory;

import java.io.IOException;

public class BaseTest extends Assertion {
    protected WebDriver driver;
    protected StringConstant stringConstant = new StringConstant();
    protected String username = "";
    protected String email = "john.dow@test.com";
    protected String password = "Testing123";

    //Pages Initialization
    protected AllPages allPages;
    protected LandingPage landingPage;
    protected SearchResultPage searchResultPage;
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp(ITestContext context) {
        driver = BrowserFactory.startApplication(driver, Browser.CHROME, stringConstant.getAppUrl());
        context.setAttribute("WebDriver", driver);
        PropertyConfigurator.configure(stringConstant.log4jFilePath());
    }

    @BeforeMethod
    public void init(ITestContext context) {
        context.setAttribute("WebDriver", driver);
        allPages = new AllPages(driver);
        landingPage = new LandingPage(driver);
        searchResultPage = new SearchResultPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.quitBrowser(driver);
    }
}
