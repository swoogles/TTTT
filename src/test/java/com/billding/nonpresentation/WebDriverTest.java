package com.billding.nonpresentation;

import com.billding.tttt.external_services.WebDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class WebDriverTest {
    private final Duration runTime = Duration.ofMillis(10);
    private final WebDriver webDriver = new WebDriver(runTime);

    @Test
    public void test_failableOperation() {
        assertEquals(
            webDriver.failableAction(),
                runTime
        );

    }

}
