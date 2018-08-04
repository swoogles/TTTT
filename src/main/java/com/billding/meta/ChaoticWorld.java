package com.billding.meta;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * This is intended to be the source of most intermittent, unpredictable failures.
 * Whether looking at the current time at 2 different points, or expecting the
 * database to be consistent in between requests, this is where the trouble should
 * arise.
 */
public class ChaoticWorld implements World {
    /* TODO Figure out how to best convey failures here. Autoblowing up might be too
        heavy-handed for the point I'm trying to make.o
        Maybe something like
        void doSomethingWith2TimesThatAreExpectedToBeTheSameMinute(
        chaoticWorld.currentMinute(),
        chaoticWorld.currentMinute())
        );
        // throw with 1 in 60 chance
        */
    @Override
    public Instant currentTime() {
        final Instant now = Instant.now();
        return now;
    }


    private final Random random = new Random();

    @Override
    public void do2AssertionsThatNeededToHappenInTheSameMinute(Duration runTimeOf) {
        int millisecondsInAMinute = 60 * 1000;
        int possibleOutComes  = millisecondsInAMinute;
        // TODO Check this math
        if( random.nextInt(Integer.MAX_VALUE) % possibleOutComes < runTimeOf.toMillis()) {
            throw new RuntimeException("Happened at the worst millisecond in the whole minute");
        }
    }

}
