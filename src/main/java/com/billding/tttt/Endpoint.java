package com.billding.tttt;

import javax.json.Json;

// TODO Not sure if EndPoint should actually encompass application
// TODO Test this class, if it's actually relevant.
public class Endpoint implements UnreliableService {
    private final Application application;
    private final Network network;
    private static final String name = "endpoint";
    // TODO Consider pulling from a property.
    private final int operationRunTime = 10;

    public Endpoint(Application application, Network network) {
        this.application = application;
        this.network = network;
    }

    public int POST(String url, Json contents) {
        return failableAction();
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
            + this.network.httpOperation(200)
            + this.application.simpleAction();
    }
}
