package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestSuiteCalculatorTest {
    @Test
    public void test_totalTestRunTime() {
        TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();
        ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(testEnvironmentParameters, componentRunTimes);
        assertTrue(testSuiteCalculator.totalTestRunTime() > 500);
    }
}
