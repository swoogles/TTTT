package com.billding.tttt;

import com.billding.meta.ServiceStatus;

/**
 * This is a bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class Browser implements UnreliableService {
    // TODO Consider taking different browser names as parameter
    private static final String name = "browser";
    private final int operationRunTime;

    public Browser(int operationRunTime) {
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
