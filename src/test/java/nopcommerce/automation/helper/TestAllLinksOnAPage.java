package nopcommerce.automation.helper;

import base.AllPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TestAllLinksOnAPage {
    AllPages allPages;

    List<String> knownBrokenLinks = new ArrayList<>();

    public TestAllLinksOnAPage() { }

    public List<String> testAllLinksOnThePage(WebDriver driver) {
        knownBrokenLinks.add("http://www.facebook.com/nopCommerce");
        knownBrokenLinks.add("https://twitter.com/nopCommerce");
        knownBrokenLinks.add("http://www.youtube.com/user/nopCommerce");
        knownBrokenLinks.add("http://docs.nopcommerce.com/");

        allPages = new AllPages(driver);
        List<WebElement> linksList = allPages.clickableLinks();
        List<String> brokenLinks =new ArrayList<>();

        for (WebElement link : linksList) {
            String href = link.getAttribute("href");
            if (href != null) {
                try {
                    URI uri = new URI(href);
                    String response = allPages.linkStatus(uri);
                    if (!response.equals("OK")) {
                        if(!knownBrokenLinks.contains(href)){
                            brokenLinks.add(href);
                        }
                    }
//                    System.out.println("Response from " + href + " is " + response);
                } catch (Exception e) {
                    System.out.println("Exception occurred while checking the link " + href);
                }
            }
        }

        int linksCount = linksList.size();
        System.out.println("Total links on the page: " + linksCount);
        return brokenLinks;
    }
}
