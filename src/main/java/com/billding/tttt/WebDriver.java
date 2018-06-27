package com.billding.tttt;

public class WebDriver implements UnreliableService {
    private static final String name = "web_driver";

    // TODO move to runtimes.properties
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
