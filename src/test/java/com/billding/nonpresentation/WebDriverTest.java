package com.billding.nonpresentation;

import com.billding.tttt.external_services.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WebDriverTest {
    private final WebDriver webDriver = new WebDriver(10);

    @Test
    public void test_failableOperation() {
        assertEquals(
            webDriver.failableAction(),
            10
        );

    }

}
