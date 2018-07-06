package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

/**
 *  Requires:
 *      Running cluster
 *      Network
 */
public class KafkaCluster implements UnreliableService {
    private final Network network;
    private final Duration operationRunTime;

    private static final String name = "kafka_cluster";

    public KafkaCluster(Network network, Duration operationRunTime) {
        this.network = network;
        this.operationRunTime = operationRunTime;
    }

    public Duration clusterAction() {
        // TODO I think I should invert this. Put the details in this layer's method, and then
        // invoke it in failableAction.
        return this.failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        // This would be more failfast if it went to the constructor. But that's probably another place that
        // I should emphasize and possibly create test variations for.
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getOperationRunTime()
                .plus(this.network.httpOperation(200));
    }
}
