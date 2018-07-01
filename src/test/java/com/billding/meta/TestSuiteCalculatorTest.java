package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestSuiteCalculatorTest {
    @Test
    public void test_totalTestRunTime() {
        TestEnvironment testEnvironment = new TestEnvironment("frozen_test_environment");
        ComponentRunTimes componentRunTimes = new ComponentRunTimes("frozen_runtimes");
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(testEnvironment, componentRunTimes);
        assertEquals(testSuiteCalculator.totalTestRunTime(), 607419);
    }
}
