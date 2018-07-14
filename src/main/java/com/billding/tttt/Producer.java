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

    private final Duration runTime;

    // TODO add intranet/network dependency
    public Producer(KafkaCluster kafkaCluster, ChaoticWorld chaoticWorld, Duration runTime) {
        this.chaoticWorld = chaoticWorld;
        this.kafkaCluster = kafkaCluster;
        this.runTime = runTime;
    }

    public Duration submitEvent() {
        return this.failableAction();
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(this.kafkaCluster.getRunTime());
    }

    @Override
    public Duration failableAction() {
        this.chaoticWorld.currentTime();
        this.kafkaCluster.clusterAction();
        return this.getRunTime();
    }
}