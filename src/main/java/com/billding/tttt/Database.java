package com.billding.tttt;

class Database implements UnreliableService {
    private final int operationRunTime = 10;
    private final Network network;

    private static final String name = "database";

    public Database(Network network) {
        this.network = network;
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.operationRunTime
                + this.network.failableAction();
    }
}
