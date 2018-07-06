package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

public class ThirdPartyResource implements UnreliableService {
    private final Duration operationRunTime;
    private final Network network;

    private final String name;

    public ThirdPartyResource(String name, Network network, Duration operationRunTime) {
        this.network = network;
        this.name = "third_party." + name;
        this.operationRunTime = operationRunTime;
    }

    public Duration communicate() {
        return this.failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getOperationRunTime()
            .plus(this.network.httpOperation(200));
    }
}
