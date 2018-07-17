package com.billding.nonpresentation;

import com.billding.tttt.external_services.WebDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

// TODO This can go away if we make a SeleniumTest unit test.
public class WebDriverTest {
    private final Duration runTime = Duration.ofMillis(10);

    @Test
    public void test_failableOperation() {
        new WebDriver(runTime);
    }

}
