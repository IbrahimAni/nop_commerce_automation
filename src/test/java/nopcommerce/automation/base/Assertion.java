package nopcommerce.automation.base;

import org.testng.asserts.SoftAssert;

public class Assertion {
    SoftAssert softAssert = new SoftAssert();

    public Assertion() {

    }

    public void assertEquals(String actual, String expected) {
        softAssert.assertEquals(actual, expected);
    }

    public void assertTrue(boolean condition) {
        softAssert.assertTrue(condition);
    }

    public void assertFalse(boolean condition) {
        softAssert.assertFalse(condition);
    }

    public void assertContains(String actual, String expected) {
        softAssert.assertTrue(actual.contains(expected));
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}
