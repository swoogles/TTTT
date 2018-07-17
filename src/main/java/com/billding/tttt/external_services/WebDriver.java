package com.billding.tttt.external_services;

import com.billding.tttt.AbstractUnreliableService;

import java.time.Duration;

/**
 * This is a weird bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class WebDriver extends AbstractUnreliableService {
    private static final String name = "web_driver";

    public WebDriver(Duration runTime) {
        super(name, runTime);
    }

}
