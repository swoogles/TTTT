package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

public class WebDriver implements UnreliableService {
    private static final String name = "web_driver";

    // TODO move to runtimes.properties?
    private final int operationRunTime = 10;

    public WebDriver() {
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
