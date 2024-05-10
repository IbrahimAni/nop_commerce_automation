package nopcommerce.automation.testcases.loginapage;

import nopcommerce.automation.base.BaseTest;
import nopcommerce.automation.helper.TestAllLinksOnAPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

public class TestAllLinksOnTheTestLogin extends BaseTest {
    Logger logger = Logger.getLogger(TestAllLinksOnTheTestLogin.class);
    TestAllLinksOnAPage testAllLinksOnAPage = new TestAllLinksOnAPage();

    @Test(description = "All links are working fine on the Login Page")
    public void testAlliLinkONTheLoginPage(){
        landingPage.clickLoginLink();
        loginPage.pageTitleIsVisible();
        List<String> brokenLinks = testAllLinksOnAPage.testAllLinksOnThePage(driver);

        if(!brokenLinks.isEmpty()){
            logger.info("Broken links on the Login page: " + brokenLinks.size());
            for (String link : brokenLinks) {
                logger.info("Broken links - " + link);
            }
        }

        assertTrue(brokenLinks.isEmpty());
        assertAll();
    }
}
