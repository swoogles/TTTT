package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.KafkaCluster;

import java.time.Duration;

/**
 * Performs simple operations on a {@link KafkaCluster}.
 */
class Producer implements UnreliableService {
    private final KafkaCluster kafkaCluster;
    private final ChaoticWorld chaoticWorld;
    private static final String name = "producer";

    private final Duration operationRunTime;

    // TODO add intranet/network dependency
    public Producer(KafkaCluster kafkaCluster, ChaoticWorld chaoticWorld, Duration operationRunTime) {
        this.chaoticWorld = chaoticWorld;
        this.kafkaCluster = kafkaCluster;
        this.operationRunTime = operationRunTime;
    }

    public Duration submitEvent() {
        return this.failableAction();
    }

    // TODO basically all of these are broken. They're not counting time of substeps. I need to either fix or remove.
    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        this.chaoticWorld.currentTime();
        return this.getOperationRunTime()
            .plus( this.kafkaCluster.clusterAction() );
    }
}