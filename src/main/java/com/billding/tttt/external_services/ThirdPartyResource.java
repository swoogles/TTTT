package com.billding.tttt.external_services;

import com.billding.tttt.ServiceStatus;
import com.billding.tttt.UnreliableService;

public class ThirdPartyResource implements UnreliableService {
    private final int operationRunTime;
    private final Network network;

    private final String name;

    public ThirdPartyResource(String name, Network network, int operationRunTime) {
        this.network = network;
        this.name = "third_party." + name;
        this.operationRunTime = operationRunTime;
    }

    public int communicate() {
        return this.failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getOperationRunTime()
            + this.network.httpOperation(200);
    }
}
