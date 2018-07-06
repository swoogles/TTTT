package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

// TODO Is there any sub-service of network that makes sense?
//  DNS?
public class Network implements UnreliableService {
    private static final String name = "network";

    private final Duration operationRunTime;

    public Network(Duration operationRunTime) {
        this.operationRunTime = operationRunTime;
    }

    public Duration httpOperation(int httpCode) {
        return failableAction();
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
