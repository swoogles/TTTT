package com.billding.tttt;

public class ThirdPartyResource implements UnreliableService {
    // TODO move to runtimes.properties
    private final int operationRunTime = 30;
    private final Network network;

    private final String name;

    public ThirdPartyResource(String name, Network network) {
        this.network = network;
        this.name = "third_party." + name;
    }

    public int communicate() {
        return this.failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return this.getOperationRunTime()
            + this.network.httpOperation(200);
    }
}
