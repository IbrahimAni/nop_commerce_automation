package nopcommerce.automation.testcases.landing;

import nopcommerce.automation.base.BaseTest;
import nopcommerce.automation.utils.RetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TestCurrencyChanging extends BaseTest {

    @Test(dataProvider = "Currency", description = "User can change currency to $ 'US Dollar'", retryAnalyzer = RetryAnalyzer.class)
    public void testCurrencyChangingToUSD(String currency1, String currency2) {
        landingPage.waitForTwoSeconds();
        landingPage.selectCurrency("US Dollar");
        landingPage.waitForTwoSeconds();
        List<String> prices = landingPage.getPriceOfFeaturedProduct();
        for (String price : prices) {
            assertContains(price, "$");
        }
        assertAll();
        System.out.println(currency1);
        System.out.println(currency2);
    }

    @Test(description = "User can change currency to € 'Euro'", retryAnalyzer = RetryAnalyzer.class)
    public void testCurrencyChangingToEuro() {
        landingPage.waitForTwoSeconds();
        landingPage.selectCurrency("Euro");
        landingPage.waitForTwoSeconds();
        List<String> prices = landingPage.getPriceOfFeaturedProduct();
        for (String price : prices) {
            assertContains(price, "€");
        }
        assertAll();
    }

    @DataProvider(name = "Currency")
    public Object[][] currencyData() {
        return new Object[][]{
                {"USD", "US Dollar"},
                {"EUR", "Euro"}
        };
    }
}
