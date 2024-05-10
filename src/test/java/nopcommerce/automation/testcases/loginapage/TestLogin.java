package nopcommerce.automation.testcases.loginapage;

import nopcommerce.automation.base.BaseTest;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test(description = "User can login successfully")
    public void testLogin() {
        landingPage.clickLoginLink();

        loginPage.pageTitleIsVisible();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginBtn();

        landingPage.clickLogoutLink();
        String text = landingPage.getLoginLinkText();
        assertContains(text, "Log in");
        assertAll();
    }
}
