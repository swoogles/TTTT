package com.billding.tttt;

import com.billding.meta.ChaoticWorld;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class Logic extends AbstractUnreliableService {
    private final ChaoticWorld chaoticWorld;

    public Logic(ChaoticWorld chaoticWorld, Mapper mapper, Duration runTime) {
        super(null, runTime, mapper);
        this.chaoticWorld = chaoticWorld;
    }
}
