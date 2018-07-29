package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Period;

import static org.testng.Assert.assertEquals;

public class TestEnvironmentTest {
    private final Organization organization = new Organization("solo_project");

    @Test
    public void getNumberOfDevelopers() {
        assertEquals(
            organization.getNumberOfDevelopers(),
            1
        );
    }

    @Test
    public void getRandomDeveloper() {
        assertEquals(
            organization.getRandomDeveloper(),
            "Bill"
        );
    }

    @Test
    public void getTimeWindow() {
        assertEquals(
            organization.getTimeWindow(),
            Period.ofWeeks(2)
        );
    }

    @Test
    public void getNumberOfTimesTestWillBeRun() {
        assertEquals(
                organization.getNumberOfTimesTestWillBeRun(),
                560
        );
    }

}
