package com.billding.nonpresentation;

import com.billding.tttt.Browser;
import org.testng.annotations.Test;

import java.time.Duration;

// TODO This can go away if we make a SeleniumTest unit test.
public class BrowserMockedTest {
    private static final Duration runTime = Duration.ofMillis(30);

    @Test
    public void test_specific() {
        new Browser(runTime);
    }
}
