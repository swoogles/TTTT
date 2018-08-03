package com.billding.meta;

import java.time.Duration;
import java.time.Instant;

public class PlatonicWorld implements World {
    @Override
    public Instant currentTime() {
        // TODO Get proper format
        return Instant.parse("2018-01-01T00:00:00Z");
    }

    @Override
    public void do2AssertionsThatNeededToHappenInTheSameMinute(Duration runTimeOfOperationsInBetween) {
        // No need for an actual body here. Lack of failure is the important bit.
    }
}
