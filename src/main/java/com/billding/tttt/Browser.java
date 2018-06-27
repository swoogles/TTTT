package com.billding.tttt;

public class Browser implements UnreliableService {

    private static final String name = "browser";
    private final int operationRunTime = 30;

    public Browser() { }

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
