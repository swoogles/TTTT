package com.billding.tttt;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class Logic extends AbstractUnreliableService {

    public Logic(Mapper mapper, Duration runTime) {
        super(null, runTime, mapper);
    }
}
