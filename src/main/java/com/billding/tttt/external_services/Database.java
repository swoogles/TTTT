package com.billding.tttt.external_services;

import com.billding.tttt.ServiceStatus;
import com.billding.tttt.UnreliableService;

public class Database implements UnreliableService {
    private final int operationRunTime;
    private final Network network;

    private static final String name = "database";

    public Database(Network network, int operationRunTime) {
        this.network = network;
        this.operationRunTime = operationRunTime;
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getOperationRunTime()
                + this.network.failableAction();
    }
}