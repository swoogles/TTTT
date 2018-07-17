package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Period;

import static org.testng.Assert.assertEquals;

public class TestEnvironmentTest {
    private final TestEnvironment testEnvironment = new TestEnvironment("solo_project");

    @Test
    public void getNumberOfDevelopers() {
        assertEquals(
            testEnvironment.getNumberOfDevelopers(),
            1
        );
    }

    @Test
    public void getRandomDeveloper() {
        assertEquals(
            testEnvironment.getRandomDeveloper(),
            "Bill"
        );
    }

    @Test
    public void getTimeWindow() {
        assertEquals(
            testEnvironment.getTimeWindow(),
            Period.ofWeeks(2)
        );
    }

    @Test
    public void getNumberOfTimesTestWillBeRun() {
        assertEquals(
                testEnvironment.getNumberOfTimesTestWillBeRun(),
                560
        );
    }

}
