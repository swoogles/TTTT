package com.billding.tttt;

import com.billding.meta.World;
import com.billding.tttt.external_services.KafkaCluster;

import java.time.Duration;

/**
 * Performs simple operations on a {@link KafkaCluster}.
 */
public class Producer extends AbstractUnreliableService {
    // TODO find an approriate ChaoticWorld use or remove
    private final World world;
    private static final String name = "producer";

    // TODO add intranet/network dependency
    public Producer(KafkaCluster kafkaCluster, World world, Duration runTime) {
        super(null, runTime, kafkaCluster);
        this.world = world;
    }

}