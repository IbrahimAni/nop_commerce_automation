package nopcommerce.automation.testcases.searchResult;

import nopcommerce.automation.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearch extends BaseTest{
    String searchText = "HTC";

    @Test(description = "User can search for a product")
    public void testSearchProduct() {
        landingPage.enterSearchText(searchText);
        landingPage.clickSearchBtn();

        searchResultPage.searchResultTitleIsVisible();
        List<String> items = searchResultPage.getSearchResultItems();
        for (String item : items) {
            assertContains(item, "HTC");
        }
        assertAll();
    }
}
