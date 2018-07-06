package com.billding.tttt;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.external_services.Network;

import java.time.Duration;

/**
 *  Requires:
 *      Network (I think LAN is a better word)
 */
public class Intranet implements UnreliableService {
    private final Duration operationRunTime;
    private final Network network;

    private static final String name = "intranet";

    public Intranet(Network network, Duration operationRunTime) {
        this.network = network;
        this.operationRunTime = operationRunTime;
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getOperationRunTime()
                .plus(this.network.failableAction());
    }
}
