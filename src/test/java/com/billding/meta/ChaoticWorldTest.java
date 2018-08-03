package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.testng.Assert.assertTrue;

public class ChaoticWorldTest {
    private final World world = new ChaoticWorld();

    @Test
    public void test_currentTime() {
        assertTrue(
        world.currentTime().isAfter(Instant.parse("1970-01-01T00:00:00Z"))
        );
    }

    @Test
    public void test_do2AssertionsThatNeededToHappenInTheSameMinute() {
        world.do2AssertionsThatNeededToHappenInTheSameMinute(Duration.ofMillis(0));
    }

    @Test
    public void getWorld_chaotic() {
        World.getWorld("chaotic");
    }

    @Test
    public void getWorld_platonic() {
        World.getWorld("platonic");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getWorld_invalid() {
        World.getWorld("invalid");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void forceAssertionFailure() {
        World chaotic = World.getWorld("chaotic");
        chaotic.do2AssertionsThatNeededToHappenInTheSameMinute(Duration.ofHours(1));

    }
}
