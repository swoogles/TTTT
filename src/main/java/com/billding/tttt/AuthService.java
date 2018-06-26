package com.billding.tttt;

// TODO Should this take a Database parameter?
public class AuthService implements UnreliableService {
    private final Intranet intranet;
    private final int operationRunTime;
    private static final String name = "auth_service";

    public AuthService(Intranet intranet, int operationRunTime) {
        this.intranet = intranet;
        this.operationRunTime = operationRunTime;
    }

    public int authenticateUser(String username, String password) { // Take params here.
        return this.intranet.failableAction();
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
            + this.intranet.failableAction();
    }
}
