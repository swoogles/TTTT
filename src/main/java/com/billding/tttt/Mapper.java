package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.external_services.Database;

class Mapper implements UnreliableService {
    private final int operationRunTime;
    private final Database database;
    private final ChaoticWorld chaoticWorld;
    private static final String name = "Mapper";

    public Mapper(Database database, ChaoticWorld chaoticWorld, int operationRunTime) {
        this.chaoticWorld = chaoticWorld;
        this.database = database;
        this.operationRunTime = operationRunTime;
    }

    public int CRUD_query() {
        return this.failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }


    @Override
    public int failableAction() {
        this.chaoticWorld.currentTime();
        return this.getOperationRunTime()
            + this.database.failableAction();
    }
}