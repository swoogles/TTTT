package com.billding.meta;

import java.time.Duration;
import java.time.Instant;

public interface World {
    void do2AssertionsThatNeededToHappenInTheSameMinute(
            Duration runTime
    );








    /* TODO Figure out how to best convey failures here. Autoblowing up might be too
            heavy-handed for the point I'm trying to make.o
            Maybe something like
            void doSomethingWith2TimesThatAreExpectedToBeTheSameMinute(
            chaoticWorld.currentMinute(),
            chaoticWorld.currentMinute())
            );
            // throw with 1 in 60 chance
            */
    Instant currentTime();

    static World getWorld(String worldType) {
        if ("chaotic".equals(worldType)) {
            return new ChaoticWorld();
        } else if ( "platonic".equals(worldType) ) {
            return new PlatonicWorld();
        } else {
                throw new IllegalArgumentException("Not a valid world type");
        }
    }
}
