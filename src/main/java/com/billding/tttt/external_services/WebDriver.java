package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

/**
 * This is a weird bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class WebDriver implements UnreliableService {
    private static final String name = "web_driver";

    private final int operationRunTime;

    public WebDriver(int operationRunTime) {
        this.operationRunTime = operationRunTime;
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getOperationRunTime();
    }
}
