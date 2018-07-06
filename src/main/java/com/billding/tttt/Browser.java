package com.billding.tttt;

import com.billding.meta.ServiceStatus;

import java.time.Duration;

/**
 * This is a bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class Browser implements UnreliableService {
    // TODO Consider taking different browser names as parameter
    private static final String name = "browser";
    private final Duration operationRunTime;

    public Browser(Duration operationRunTime) {
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
