package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

// TODO Is there any sub-service of network that makes sense?
//  DNS?
public class Network implements UnreliableService {
    private static final String name = "network";

    private final Duration runTime;

    public Network(Duration runTime) {
        this.runTime = runTime;
    }

    public Duration httpOperation(int httpCode) {
        return failableAction();
    }

    @Override
    public Duration getRunTime() {
        return this.runTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getRunTime();
    }
}
