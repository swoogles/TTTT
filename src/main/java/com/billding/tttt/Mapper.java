package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.Database;

import java.time.Duration;

/**
 * Performs simple operations on a {@link Database}.
 */
class Mapper implements UnreliableService {
    private final Duration operationRunTime;
    private final Database database;
    private final ChaoticWorld chaoticWorld;
    private static final String name = "Mapper";

    public Mapper(Database database, ChaoticWorld chaoticWorld, Duration operationRunTime) {
        this.chaoticWorld = chaoticWorld;
        this.database = database;
        this.operationRunTime = operationRunTime;
    }

    public Duration CRUD_query() {
        return this.failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }


    @Override
    public Duration failableAction() {
        this.chaoticWorld.currentTime();
        return this.getOperationRunTime()
            .plus( this.database.failableAction() );
    }
}