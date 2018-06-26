package com.billding.tttt;

// TODO Is there any sub-service of network that makes sense?
//  DNS?
public class Network implements UnreliableService {
    private static final String name = "network";

    private final int operationRunTime;

    public Network(int operationRunTime) {
        this.operationRunTime = operationRunTime;
    }

    public int httpOperation(int httpCode) {
        return failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.operationRunTime;
    }
}
