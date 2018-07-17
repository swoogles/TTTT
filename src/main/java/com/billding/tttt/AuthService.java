package com.billding.tttt;

import com.billding.meta.ServiceStatus;

import java.time.Duration;

// TODO Should this take a Database parameter?
/**
 * Performs simple authentication operations that involve a lookup on an {@link Intranet}.
 */
public class AuthService implements UnreliableService {
    private final Intranet intranet;
    private final Duration runTime;
    private static final String name = "auth_service";

    public AuthService(Intranet intranet, Duration runTime) {
        this.intranet = intranet;
        this.runTime = runTime;
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(this.intranet.getRunTime());
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(this.name);
        return
            this.getRunTime()
            .plus(this.intranet.failableAction());
    }
}
