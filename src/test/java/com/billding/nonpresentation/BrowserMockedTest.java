package com.billding.nonpresentation;

import com.billding.tttt.Browser;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class BrowserMockedTest {
    // TODO Pass as constructor arg
    private static final Duration runTime = Duration.ofMillis(30);

    @DataProvider(name = "browsers")
    public static Object[][] testData() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Browser(runTime)
        );

    }

    @Test(dataProvider = "browsers")
    public void test_specific(String developer, Browser browser) {
        assertEquals(runTime, browser.failableAction());
    }
}
