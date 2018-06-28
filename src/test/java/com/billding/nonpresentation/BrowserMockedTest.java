package com.billding.nonpresentation;

import com.billding.tttt.Browser;
import com.billding.tttt.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BrowserMockedTest {
    // TODO Pass as constructor arg
    private static final int browserOperationRunTime = 30;

    @DataProvider(name = "browsers")
    public static Object[][] testData() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Browser()
        );

    }

    @Test(dataProvider = "browsers")
    public void test_specific(String developer, Browser browser) {
        assertEquals(browserOperationRunTime, browser.failableAction());
    }
}
