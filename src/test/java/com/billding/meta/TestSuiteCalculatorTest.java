package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestSuiteCalculatorTest {
    @Test
    public void test_totalTestRunTime() {
        TestEnvironment testEnvironment = new TestEnvironment("solo_project");
        ComponentRunTimes componentRunTimes = new ComponentRunTimes("frozen_runtimes");
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(testEnvironment, componentRunTimes);
        assertEquals(testSuiteCalculator.totalTestRunTime(), 56879);
    }
}
