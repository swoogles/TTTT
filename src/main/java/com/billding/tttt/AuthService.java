package com.billding.tttt;

import com.billding.meta.ServiceStatus;

import java.time.Duration;

// TODO Should this take a Database parameter?
/**
 * Performs simple authentication operations that involve a lookup on an {@link Intranet}.
 */
public class AuthService implements UnreliableService {
    private final Intranet intranet;
    private final Duration operationRunTime;
    private static final String name = "auth_service";

    public AuthService(Intranet intranet, Duration operationRunTime) {
        this.intranet = intranet;
        this.operationRunTime = operationRunTime;
    }

    public Duration authenticateUser(String username, String password) { // Take params here.
        return failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getOperationRunTime()
            .plus(this.intranet.failableAction());
    }
}
