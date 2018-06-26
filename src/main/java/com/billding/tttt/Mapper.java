package com.billding.tttt;

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

    public int CRUD_query() { // I think this should accept a param or 2
        return this.failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }


    @Override
    public int failableAction() {
        this.chaoticWorld.currentTime();
        return this.operationRunTime
            + this.database.failableAction();
    }
}