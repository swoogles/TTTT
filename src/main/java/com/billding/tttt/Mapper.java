package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.Database;

import java.time.Duration;

/**
 * Performs simple operations on a {@link Database}.
 */
public class Mapper extends AbstractUnreliableService {
    private final ChaoticWorld chaoticWorld;
    private static final String name = "Mapper";

    public Mapper(Database database, ChaoticWorld chaoticWorld, Duration runTime) {
        super(null, runTime, database);
        this.chaoticWorld = chaoticWorld;
    }

}