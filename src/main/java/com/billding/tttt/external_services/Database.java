package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

public class Database implements UnreliableService {
    private final Duration operationRunTime;
    private final Network network;

    private static final String name = "database";

    public Database(Network network, Duration operationRunTime) {
        this.network = network;
        this.operationRunTime = operationRunTime;
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime
            .plus(this.network.getOperationRunTime());
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getOperationRunTime()
                .plus( this.network.failableAction() );
    }
}
