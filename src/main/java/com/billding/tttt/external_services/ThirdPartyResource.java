package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

public class ThirdPartyResource implements UnreliableService {
    private final Duration runTime;
    private final Network network;

    private final String name;

    public ThirdPartyResource(String name, Network network, Duration runTime) {
        this.network = network;
        this.name = "third_party." + name;
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
        return this.getRunTime()
            .plus(this.network.httpOperation(200));
    }
}
