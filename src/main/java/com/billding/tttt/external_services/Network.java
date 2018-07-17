package com.billding.tttt.external_services;

import com.billding.tttt.AbstractUnreliableService;

import java.time.Duration;

// TODO Is there any sub-service of network that makes sense?
//  DNS?
public class Network extends AbstractUnreliableService {
    private static final String name = "network";

    public Network(Duration runtime) {
        super(Network.name, runtime);
    }

}


