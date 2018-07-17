package com.billding.tttt.external_services;

import com.billding.tttt.AbstractUnreliableService;

import java.time.Duration;

public class ThirdPartyResource extends AbstractUnreliableService {

    public ThirdPartyResource(String name, Network network, Duration runTime) {
        super("third_party." + name, runTime, network);
    }

}
