package com.billding.meta;

import java.time.Instant;
import java.util.Random;

public class ChaoticWorld {
    /* TODO Figure out how to best convey failures here. Autoblowing up might be too
        heavy-handed for the point I'm trying to make.o
        Maybe something like
        void doSomethingWith2TimesThatAreExpectedToBeTheSameMinute(
        chaoticWorld.currentMinute(),
        chaoticWorld.currentMinute())
        );
        // throw with 1 in 60 chance
        */
    public Instant currentTime() {
        // TODO Make % a parameter/property
        final Instant now = Instant.now();
        // TODO Reinstate when appropriate
//        if (now.toEpochMilli() % 1000 == 0)
//            throw new RuntimeException("Uh oh! This happened to round at the wrong moment! Fail!");
        return now;
    }


    private final Random random = new Random();

    public void do2AssertionsThatNeededToHappenInTheSameMinute(int runTimeOfOperationsInBetween) {
        int millisecondsAnAnHour = 60 * 1000;
        int possibleOutComes  = millisecondsAnAnHour;
        // TODO Reinstate when appropriate
        /*
        if( Math.abs(random.nextInt()) % possibleOutComes < runTimeOfOperationsInBetween) {
            throw new RuntimeException("Happened at the worst millisecond in the whole hour");
        }
        */
    }

}
