package com.billding.tttt;

/**
 *  Requires:
 *      Running cluster
 *      Network
 */
public class KafkaCluster implements UnreliableService {
    private final Network network;
    private final int operationRunTime = 20;

    private static final String name = "kafka_cluster";

    public KafkaCluster(Network network) {
        this.network = network;
    }

    public int clusterAction() {
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
            this.operationRunTime
            + this.network.failableAction();
    }
}
