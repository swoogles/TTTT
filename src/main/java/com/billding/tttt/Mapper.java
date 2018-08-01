package com.billding.tttt;

import com.billding.meta.World;
import com.billding.tttt.external_services.Database;

import java.time.Duration;

/**
 * Performs simple operations on a {@link Database}.
 */
public class Mapper extends AbstractUnreliableService {
    private final World world;
    private static final String name = "Mapper";

    public Mapper(Database database, World world, Duration runTime) {
        super(null, runTime, database);
        this.world = world;
    }

}