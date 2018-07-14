package com.billding.static_versions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

// TODO Decide if I can actually be lazy with test coverage here,
// since this is all for the sake of a strawman class.
public class StaticLogicTest {
    @Test
    public void test_simple() {
        assertNotNull(
                new StaticLogic()
        );
        StaticLogic.facilityLevelOperation();
        StaticLogic.failableAction();
        StaticLogic.getRunTime();
    }
}
