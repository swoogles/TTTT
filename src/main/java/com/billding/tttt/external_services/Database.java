package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

public class Database implements UnreliableService {
    private final Duration runTime;
    private final Network network;

    private static final String name = "database";

    public Database(Network network, Duration runTime) {
        this.network = network;
        this.runTime = runTime;
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(this.network.getRunTime());
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getRunTime()
                .plus( this.network.failableAction() );
    }
}
