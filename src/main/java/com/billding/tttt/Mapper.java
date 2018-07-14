package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.Database;

import java.time.Duration;

/**
 * Performs simple operations on a {@link Database}.
 */
public class Mapper implements UnreliableService {
    private final Duration runTime;
    private final Database database;
    private final ChaoticWorld chaoticWorld;
    private static final String name = "Mapper";

    public Mapper(Database database, ChaoticWorld chaoticWorld, Duration runTime) {
        this.chaoticWorld = chaoticWorld;
        this.database = database;
        this.runTime = runTime;
    }

    public Duration CRUD_query() {
        return this.failableAction();
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(this.database.getRunTime());
    }


    @Override
    public Duration failableAction() {
        this.chaoticWorld.currentTime();
        return this.getRunTime()
            .plus( this.database.failableAction() );
    }
}