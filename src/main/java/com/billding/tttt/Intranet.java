package com.billding.tttt;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.external_services.Network;

import java.time.Duration;

/**
 *  Requires:
 *      Network (I think LAN is a better word)
 */
public class Intranet implements UnreliableService {
    private final Duration runTime;
    private final Network network;

    private static final String name = "intranet";

    public Intranet(Network network, Duration runTime) {
        this.network = network;
        this.runTime = runTime;
    }

    @Override
    public Duration getRunTime() {
        return this.runTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getRunTime()
                .plus(this.network.failableAction());
    }
}
