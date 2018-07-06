package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

/**
 * This is a weird bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class WebDriver implements UnreliableService {
    private static final String name = "web_driver";

    private final Duration operationRunTime;

    public WebDriver(Duration operationRunTime) {
        this.operationRunTime = operationRunTime;
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getOperationRunTime();
    }
}
