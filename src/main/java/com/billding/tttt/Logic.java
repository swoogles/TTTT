package com.billding.tttt;

import com.billding.meta.World;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class Logic extends AbstractUnreliableService {
    private final World world;

    public Logic(World world, Mapper mapper, Duration runTime) {
        super(null, runTime, mapper);
        this.world = world;
    }
}
