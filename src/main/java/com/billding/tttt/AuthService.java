package com.billding.tttt;

import java.time.Duration;

// TODO Should this take a Database parameter?
/**
 * Performs simple authentication operations that involve a lookup on an {@link Intranet}.
 */
public class AuthService extends AbstractUnreliableService {
    private static final String name = "auth_service";

    public AuthService(Intranet intranet, Duration runTime) {
        super(name, runTime, intranet);
    }

}
