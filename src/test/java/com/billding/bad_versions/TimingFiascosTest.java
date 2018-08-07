package com.billding.bad_versions;

import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TimingFiascosTest {
    private EventLogic eventLogic = null;
    interface Event {}
    interface EventLogic {
        List<Event> inLast10Minutes(Instant instant);
        List<Event> inLastMinute(Instant instant);
    }










    @Test
    public void timeSensitiveTest() {
        List<Event> eventsInLast10Minutes =
                eventLogic.inLast10Minutes(Instant.now());

        // ... Other commands/assertions ...

        List<Event> eventsFromThisMinute =
                eventLogic.inLastMinute(Instant.now());

        assertTrue(
                eventsInLast10Minutes.containsAll(
                        eventsFromThisMinute
                )

        );
    }












    @Test
    public void timeSensitiveTest_improved() {
        Instant now = Instant.now();
        List<Event> eventsInLast10Minutes =
                eventLogic.inLast10Minutes(now);

        // ... Other commands/assertions ...

        List<Event> eventsFromThisMinute =
                eventLogic.inLastMinute(now);

        assertTrue(
                eventsInLast10Minutes.containsAll(
                        eventsFromThisMinute
                )

        );
    }














    Clock clock =
            Clock.fixed(
                    Instant.parse("2018-08-08T00:00:00Z"),
                    ZoneId.systemDefault());
    @Test
    public void timeSensitiveTest_betterStill() {
        Instant now = clock.instant();
        List<Event> eventsInLast10Minutes =
                eventLogic.inLast10Minutes(now);

        // ... Other commands/assertions ...

        List<Event> eventsFromThisMinute =
                eventLogic.inLastMinute(now);

        assertTrue(
                eventsInLast10Minutes.containsAll(
                        eventsFromThisMinute
                )

        );
    }
















    Clock clock2 =
            Clock.fixed(
                    Instant.parse("2018-08-08T00:00:00Z"),
                    ZoneId.of("America/Denver"));
    @Test
    public void timeInsensitiveTest() {
        Instant now = clock2.instant();
        List<Event> eventsInLast10Minutes =
                eventLogic.inLast10Minutes(now);

        // ... Other commands/assertions ...

        List<Event> eventsFromThisMinute =
                eventLogic.inLastMinute(now);

        assertTrue(
                eventsInLast10Minutes.containsAll(
                        eventsFromThisMinute
                )

        );
    }
}
