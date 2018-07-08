package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

/**
 * This is a weird bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class WebDriver implements UnreliableService {
    private static final String name = "web_driver";

    private final Duration runTime;

    public WebDriver(Duration runTime) {
        this.runTime = runTime;
    }

    @Override
    public Duration getRunTime() {
        return this.runTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getRunTime();
    }
}
