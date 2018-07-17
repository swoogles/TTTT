package com.billding.tttt.external_services;

import com.billding.tttt.AbstractUnreliableService;

import java.time.Duration;

public class Database extends AbstractUnreliableService {
    private static final String name = "database";

    public Database(Network network, Duration runTime) {
        super(name, runTime, network);
    }

}
