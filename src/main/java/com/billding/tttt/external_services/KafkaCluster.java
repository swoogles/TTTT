package com.billding.tttt.external_services;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.AbstractUnreliableService;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

/**
 *  Requires:
 *      Running cluster
 *      Network
 */
public class KafkaCluster extends AbstractUnreliableService {

    private static final String name = "kafka_cluster";

    public KafkaCluster(Network network, Duration runTime) {
        super(name, runTime, network);
    }

}
