package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.KafkaCluster;

class Producer implements UnreliableService {
    private final KafkaCluster kafkaCluster;
    private final ChaoticWorld chaoticWorld;
    private static final String name = "producer";

    private final int operationRunTime;

    // TODO add intranet/network dependency
    public Producer(KafkaCluster kafkaCluster, ChaoticWorld chaoticWorld, int operationRunTime) {
        this.chaoticWorld = chaoticWorld;
        this.kafkaCluster = kafkaCluster;
        this.operationRunTime = operationRunTime;
    }

    public int submitEvent() {
        return this.failableAction();
    }

    // TODO basically all of these are broken. They're not counting time of substeps. I need to either fix or remove.
    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        this.chaoticWorld.currentTime();
        return this.getOperationRunTime()
            + this.kafkaCluster.clusterAction();
    }
}