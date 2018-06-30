package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Instant;

import static org.testng.Assert.assertTrue;

public class ChaoticWorldTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @Test
    public void test_currentTime() {
        assertTrue(
        chaoticWorld.currentTime().isAfter(Instant.parse("1970-01-01T00:00:00Z"))
        );
    }

    @Test
    public void test_do2AssertionsThatNeededToHappenInTheSameMinute() {
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(0);
    }
}
