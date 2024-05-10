package nopcommerce.automation.testcases.searchResult;

import nopcommerce.automation.base.BaseTest;
import nopcommerce.automation.helper.TestAllLinksOnAPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

public class TestAllLinksOnSearchResultPage extends BaseTest {
    Logger logger = Logger.getLogger(TestAllLinksOnSearchResultPage.class);
    TestAllLinksOnAPage testAllLinksOnAPage = new TestAllLinksOnAPage();

    @Test(description = "All links are working fine on the Search Result Page")
    public void testAllLinksOnTheSearchResultPage() {
        landingPage.enterSearchText("HTC");
        landingPage.clickSearchBtn();

        searchResultPage.searchResultTitleIsVisible();

        List<String> brokenLinks = testAllLinksOnAPage.testAllLinksOnThePage(driver);

        if(!brokenLinks.isEmpty()) {
            logger.info("Broken links on the Search Result page: " + brokenLinks.size());
            for (String link : brokenLinks) {
                logger.info("Broken links - " + link);
            }
        }

        assertTrue(brokenLinks.isEmpty());
        assertAll();
    }
}
