package com.billding.tttt;

import com.billding.tttt.external_services.Network;

import java.time.Duration;

/**
 *  Requires:
 *      Network (I think LAN is a better word)
 */
public class Intranet extends AbstractUnreliableService {
    private static final String name = "intranet";

    public Intranet(Network network, Duration runTime) {
        super(name, runTime, network);
    }

}
