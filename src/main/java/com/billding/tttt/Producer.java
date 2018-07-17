package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.KafkaCluster;

import java.time.Duration;

/**
 * Performs simple operations on a {@link KafkaCluster}.
 */
public class Producer extends AbstractUnreliableService {
    // TODO find an approriate ChaoticWorld use or remove
    private final ChaoticWorld chaoticWorld;
    private static final String name = "producer";

    // TODO add intranet/network dependency
    public Producer(KafkaCluster kafkaCluster, ChaoticWorld chaoticWorld, Duration runTime) {
        super(null, runTime, kafkaCluster);
        this.chaoticWorld = chaoticWorld;
    }

}