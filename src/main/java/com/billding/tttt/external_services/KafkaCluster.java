package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

/**
 *  Requires:
 *      Running cluster
 *      Network
 */
public class KafkaCluster implements UnreliableService {
    private final Network network;
    private final int operationRunTime;

    private static final String name = "kafka_cluster";

    public KafkaCluster(Network network, int operationRunTime) {
        this.network = network;
        this.operationRunTime = operationRunTime;
    }

    public int clusterAction() {
        // TODO I think I should invert this. Put the details in this layer's method, and then
        // invoke it in failableAction.
        return this.failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        // This would be more failfast if it went to the constructor. But that's probably another place that
        // I should emphasize and possibly create test variations for.
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getOperationRunTime()
            + this.network.httpOperation(200);
    }
}
