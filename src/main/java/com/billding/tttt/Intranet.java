package com.billding.tttt;

import com.billding.tttt.external_services.Network;

/**
 *  Requires:
 *      Network (I think LAN is a better word)
 */
public class Intranet implements UnreliableService {
    private final int operationRunTime = 10;
    private final Network network;

    private static final String name = "intranet";

    public Intranet(Network network) {
        this.network = network;
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
