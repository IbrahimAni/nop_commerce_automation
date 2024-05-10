package nopcommerce.automation.testcases.landing;

import nopcommerce.automation.base.BaseTest;
import nopcommerce.automation.helper.TestAllLinksOnAPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

public class TestAllLinksOnLandingOnPage extends BaseTest {
    Logger logger = Logger.getLogger(TestAllLinksOnLandingOnPage.class);
    TestAllLinksOnAPage testAllLinksOnAPage = new TestAllLinksOnAPage();

    @Test(description = "All links are working fine")
    public void testAllLinksOnTheLandingPage() {
        List<String> brokenLinks = testAllLinksOnAPage.testAllLinksOnThePage(driver);

        if(!brokenLinks.isEmpty()) {
            logger.info("Broken links on the Landing page: " + brokenLinks.size());
            for (String link : brokenLinks) {
                logger.info("Broken links - " + link);
            }
        }

        assertTrue(brokenLinks.isEmpty());
        assertAll();
    }
}
