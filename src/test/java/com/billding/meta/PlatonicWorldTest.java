package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Instant;

import static org.testng.Assert.assertEquals;

public class PlatonicWorldTest {
    @Test
    public void currentTime() {
        World world = new PlatonicWorld();
        assertEquals(
        world.currentTime(),
                Instant.parse("2018-01-01T00:00:00Z")
        );
    }
}
